/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.service.impl;

import com.rms.feasty.constants.OrderItemStatusEnum;
import com.rms.feasty.constants.OrderStatusEnum;
import com.rms.feasty.dto.order.*;
import com.rms.feasty.entity.Order;
import com.rms.feasty.entity.OrderItem;
import com.rms.feasty.exceptions.ItemNotFoundException;
import com.rms.feasty.exceptions.OrderNotFoundException;
import com.rms.feasty.mapper.OrderItemMapper;
import com.rms.feasty.mapper.OrderMapper;
import com.rms.feasty.repository.ItemRepository;
import com.rms.feasty.repository.OrderItemRepository;
import com.rms.feasty.repository.OrderRepository;
import com.rms.feasty.repository.TableRepository;
import com.rms.feasty.service.ItemService;
import com.rms.feasty.service.OrderService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final static Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemService itemService;
    private final ItemRepository itemRepository;
    private final TableRepository tableRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ItemService itemService, ItemRepository itemRepository, TableRepository tableRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.itemService = itemService;
        this.itemRepository = itemRepository;
        this.tableRepository = tableRepository;
    }


    @Override
    public OrderResponse addOrder(OrderRequest orderRequest) {
        logger.debug("Inside: addOrder");
        if (orderRequest == null) {
            logger.warn("Null order. Returning null.");
            return null;
        }

        Order order = buildOrderFromOrderRequest(orderRequest);

        try {
            order = orderRepository.save(order);
        } catch (Exception e) {
            logger.error("Error while initiating the order. Reason- {}", e.getMessage());
            return null;
        }
        logger.info(
                "Order {} initiated successfully at table no. {}",
                order.getId(),
                order.getOrderTable().getId()
        );

        return OrderMapper.buildOrderResponse(order);
    }

    @Override
    public List<OrderResponse> getAllOpenOrders() {
        logger.debug("Inside: getAllOpenOrders");

        List<Order> orders = orderRepository.findAllByStatus(OrderStatusEnum.OPENED, Sort.by(Sort.Order.asc("id")));
        logger.debug("Found {} open orders", orders.size());

        return OrderMapper.buildOrderResponse(orders);
    }

    @Override
    @Transactional
    public List<OrderItemResponse> addItemsToOrder(int orderId, List<OrderItemRequest> orderItemRequests) throws OrderNotFoundException, ItemNotFoundException {
        logger.debug("Inside: addItemsToOrder");
        // validate if the order exists
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> {
                    logger.error("Order {} does not exist.", orderId);
                    return new OrderNotFoundException();
                });

        List<OrderItem> updatedItems = new ArrayList<>();
        List<OrderItem> orderItems = buildOrderItemsFromOrderItemsRequests(orderItemRequests);

        List<OrderItem> existingOrderItems = orderItemRepository.getAllByOrder(order, Sort.by(Sort.Order.asc("id")));
        if (!CollectionUtils.isEmpty(existingOrderItems)) {
            // calculate DIFF with existing items & update quantity
            // remove those items from orderItems list
            List<OrderItem> itemsToBeUpdated = updateExistingItemsInOrder(existingOrderItems, orderItems);

            if (!itemsToBeUpdated.isEmpty()) {
                // update existing items
                updatedItems.addAll(orderItemRepository.saveAll(itemsToBeUpdated));
                logger.info("{} items updated in order no. {}", itemsToBeUpdated.size(), orderId);
            }
        }

        // add new items; if-any
        if (!CollectionUtils.isEmpty(orderItems)) {
            updatedItems.addAll(orderItemRepository.saveAll(orderItems));
            logger.info("Added {} items to order no. {}", orderItems.size(), orderId);
        }

        return OrderItemMapper.buildOrderItemResponse(updatedItems);
    }

    @Override
    public DetailedOrderResponse getOrderDetailsById(int orderId) throws OrderNotFoundException {
        logger.debug("Inside: getOrderDetailsById");

        List<OrderDetailRow> orderDetailRows = orderRepository.getDetailedOrderById(orderId);
        if (orderDetailRows.isEmpty()) {
            throw new OrderNotFoundException(orderId);
        }

        DetailedOrderResponse response = buildDetailedOrderResponseFromOrderDetailRows(orderDetailRows);
        if (response != null)
            logger.info("Returning details of order-{}", orderId);
        else
            throw new OrderNotFoundException(orderId);

        return response;
    }

    // -------------------------- HELPER METHODS --------------------------

    private Order buildOrderFromOrderRequest(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderTable(tableRepository.getReferenceById(orderRequest.getTableId()));
        order.setStatus(OrderStatusEnum.OPENED);
        return order;
    }

    private List<OrderItem> buildOrderItemsFromOrderItemsRequests(List<OrderItemRequest> orderItemRequests) {
        List<OrderItem> orderItems = new ArrayList<>();
        orderItemRequests.forEach(orderItemRequest -> orderItems.add(buildOrderItemFromOrderItemsRequest(orderItemRequest)));
        return orderItems;
    }

    OrderItem buildOrderItemFromOrderItemsRequest(OrderItemRequest orderItemRequest) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(itemRepository.getReferenceById(orderItemRequest.getItemId()));
        orderItem.setOrder(orderRepository.getReferenceById(orderItemRequest.getOrderId()));
        // orderItem.setPendingCount(orderItemRequest.getQuantity()); To be fixed- once chef view is implemented
        orderItem.setCount(orderItemRequest.getQuantity()); // workaround till chef-view is implemented
        return orderItem;
    }


    /**
     * <ol>
     * <li> calculate DIFF with <code>existingOrderItems</code> & <code>newOrderItems</code></li>
     * <li> update quantity and status of existing items </li>
     * <li> remove those items from <code>newOrderItems</code> list </li>
     * </ol>
     *
     * @param existingOrderItems Existing items in the given order
     * @param newOrderItems      New items added to the order
     * @return {@link List} of {@link OrderItem} to be updated in the order
     *
     */
    private List<OrderItem> updateExistingItemsInOrder(List<OrderItem> existingOrderItems, List<OrderItem> newOrderItems) {

        Map<Integer, OrderItem> itemIdToExistingOrderItemMap = existingOrderItems.stream().collect(Collectors.toMap(
                existingOrderItem -> existingOrderItem.getItem().getId(), existingOrderItem -> existingOrderItem, (oitem1, oitem2) -> oitem2
        ));

        Map<Integer, OrderItem> itemIdToNewOrderItemMap = newOrderItems.stream().collect(Collectors.toMap(
                newOrderItem -> newOrderItem.getItem().getId(), newOrderItem -> newOrderItem, (oitem1, oitem2) -> oitem2
        ));

        // find intersection of both the maps, i.e., items that already exist in the order
        itemIdToExistingOrderItemMap.keySet().retainAll(itemIdToNewOrderItemMap.keySet());
        Set<Integer> existingItemIds = itemIdToExistingOrderItemMap.keySet();

        // no existing orderitem found; return empty list
        if (CollectionUtils.isEmpty(existingItemIds)) {
            return Collections.emptyList();
        }

        List<OrderItem> itemsToBeUpdated = new ArrayList<>();
        for (Integer itemId : existingItemIds) {
            OrderItem existingOrderItem = itemIdToExistingOrderItemMap.get(itemId);
            OrderItem newOrderItem = itemIdToNewOrderItemMap.get(itemId);

            existingOrderItem.setPendingCount(existingOrderItem.getPendingCount() + newOrderItem.getPendingCount());
            existingOrderItem.setStatus(OrderItemStatusEnum.PENDING);

            itemsToBeUpdated.add(itemIdToExistingOrderItemMap.get(itemId));
            newOrderItems.remove(newOrderItem); // remove from the list; as this will be updated not added
        }
        return itemsToBeUpdated;
    }

    private DetailedOrderResponse buildDetailedOrderResponseFromOrderDetailRows(List<OrderDetailRow> orderDetailRows) {
        if (CollectionUtils.isEmpty(orderDetailRows)) {
            return null;
        }
        DetailedOrderResponse detailedOrderResponse = new DetailedOrderResponse();

        // populate order related details
        detailedOrderResponse.setOrderId(orderDetailRows.get(0).getOrderId());
        detailedOrderResponse.setTableId(orderDetailRows.get(0).getTableId());
        detailedOrderResponse.setStatus(orderDetailRows.get(0).getStatus().getLabel());
        detailedOrderResponse.setCustomerId(orderDetailRows.get(0).getCustomerId());

        // populate order-items related details
        List<DetailedOrderItemResponse> detailedOrderItems = new ArrayList<>();

        float totalcost = 0f;
        for (OrderDetailRow orderDetailRow : orderDetailRows) {
            DetailedOrderItemResponse response = buildDetailedOrderItemResponseFromOrderDetailRow(orderDetailRow);
            if(response != null) {
                detailedOrderItems.add(response);
                totalcost += (response.getItemsCount()) * response.getItem().getCost();
            }
        }
        detailedOrderResponse.setOrderItems(detailedOrderItems);
        detailedOrderResponse.setTotal(totalcost);

        return detailedOrderResponse;
    }

    private DetailedOrderItemResponse buildDetailedOrderItemResponseFromOrderDetailRow(OrderDetailRow orderDetailRow) {
        if(orderDetailRow.getOrderItemid() == null)
            return null;

        DetailedOrderItemResponse detailedOrderItemResponse = new DetailedOrderItemResponse();

        // populate order-item related fields
        detailedOrderItemResponse.setId(orderDetailRow.getOrderItemid());
        detailedOrderItemResponse.setItemsCount(orderDetailRow.getCount());
        detailedOrderItemResponse.setPendingItems(orderDetailRow.getPendingItems());
        detailedOrderItemResponse.setStatus(orderDetailRow.getStatus().getLabel());

        // populate item related fields
        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setItemId(orderDetailRow.getItemId());
        itemResponse.setName(orderDetailRow.getName());
        itemResponse.setDescription(orderDetailRow.getDescription());
        itemResponse.setCost(orderDetailRow.getCost());

        detailedOrderItemResponse.setItem(itemResponse);

        return detailedOrderItemResponse;
    }


}
