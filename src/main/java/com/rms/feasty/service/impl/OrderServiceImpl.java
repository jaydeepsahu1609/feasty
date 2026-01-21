/*
 * @Copyright 2026, Jaydeep Sahu
 */

package com.rms.feasty.service.impl;

import com.rms.feasty.constants.OrderItemStatusEnum;
import com.rms.feasty.constants.OrderStatusEnum;
import com.rms.feasty.dto.order.OrderRequest;
import com.rms.feasty.dto.order.OrderResponse;
import com.rms.feasty.entity.Item;
import com.rms.feasty.entity.Order;
import com.rms.feasty.entity.OrderItem;
import com.rms.feasty.entity.RestaurantTable;
import com.rms.feasty.exceptions.ItemNotFoundException;
import com.rms.feasty.exceptions.OrderNotFoundException;
import com.rms.feasty.mapper.OrderMapper;
import com.rms.feasty.repository.OrderItemRepository;
import com.rms.feasty.repository.OrderRepository;
import com.rms.feasty.repository.TableRepository;
import com.rms.feasty.service.ItemService;
import com.rms.feasty.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final static Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemService itemService;
    private final TableRepository tableRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ItemService itemService, TableRepository tableRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.itemService = itemService;
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

    private Order buildOrderFromOrderRequest(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderTable(tableRepository.getReferenceById(orderRequest.getTableId()));
        order.setStatus(OrderStatusEnum.OPENED);
        return order;
    }


    @Override
    public List<OrderResponse> getAllOpenOrders() {
        logger.debug("Inside: getAllOpenOrders");

        List<Order> orders = orderRepository.findAllByStatus(OrderStatusEnum.OPENED, Sort.by(Sort.Order.asc("id")));
        logger.debug("Found {} open orders", orders.size());

        return OrderMapper.buildOrderResponse(orders);
    }

//    @Override
//    public List<OrderItem> addItemsToOrder(int orderId, List<OrderItem> newOrderItems) throws OrderNotFoundException, ItemNotFoundException {
//        logger.debug("Inside: addItemsToOrder");
//        // validate if the order exists
//        Order order = orderRepository.findById(orderId)
//                .orElseThrow(() -> {
//                    logger.error("Order {} does not exist.", orderId);
//                    return new OrderNotFoundException();
//                });
//
//        // populate order in the orderItems.
//        newOrderItems.forEach(orderItem -> orderItem.setOrder(order));
//
//        // get Items from DB/Cache
//        List<Integer> itemIds = newOrderItems.stream().map(oi -> oi.getItem().getId()).toList();
//        if (itemIds.isEmpty()) {
//            logger.debug("Ids list is empty. Returning an empty list");
//            return Collections.emptyList();
//        }
//        List<Item> items = itemService.getItemsByIds(itemIds);
//        if (items.size() < itemIds.size()) {
//            logger.error("{}/{} items not found in DB", (itemIds.size() - items.size()), itemIds.size());
//            throw new ItemNotFoundException();
//        }
//
//        // populate item in orderItems object
//        populateItemsInOrderItems(newOrderItems, items);
//
//        List<OrderItem> existingOrderItems = order.getOrderItems();
//        updateItemsInExistingOrderItems(existingOrderItems, newOrderItems);
//
//        List<OrderItem> savedOrderItems = orderItemRepository.saveAll(newOrderItems);
//        logger.info("Successfully added {} items to Order {}", savedOrderItems.size(), orderId);
//
//        return savedOrderItems;
//    }
//
//    private void updateItemsInExistingOrderItems(List<OrderItem> existingOrderItems, List<OrderItem> newOrderItems) {
//        Map<Integer, OrderItem> itemIdsToOrderItemMap = newOrderItems.stream().collect(Collectors.toMap(OrderItem::getId, oitem -> oitem, (oitem1, oitem2) -> oitem1));
//
//        for (OrderItem existingOrderItem : existingOrderItems) {
//            int itemId = existingOrderItem.getItem().getId();
//            if (itemIdsToOrderItemMap.containsKey(itemId)) {
//                OrderItem newOrderItem = itemIdsToOrderItemMap.get(itemId);
//                // increase pendingcount of existing order
//                existingOrderItem.setPendingCount(existingOrderItem.getPendingCount() + newOrderItem.getPendingCount());
//                // now the itemstatus is pending; till it is served to the customer
//                existingOrderItem.setStatus(OrderItemStatusEnum.PENDING);
//            }
//        }
//    }
//
//    private void populateItemsInOrderItems(List<OrderItem> orderItems, List<Item> items) {
//        Map<Integer, Item> itemIdsToItemMap = items.stream().collect(Collectors.toMap(Item::getId, item -> item, (item1, item2) -> item1));
//        Map<Integer, OrderItem> itemIdsToOrderItemMap = orderItems.stream().collect(Collectors.toMap(OrderItem::getId, oitem -> oitem, (oitem1, oitem2) -> oitem1));
//
//        itemIdsToOrderItemMap.forEach((itemId, orderItem) -> orderItem.setItem(itemIdsToItemMap.get(itemId)));
//    }

}
