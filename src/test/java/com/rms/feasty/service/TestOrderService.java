/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.service;

import com.rms.feasty.constants.OrderStatusEnum;
import com.rms.feasty.dto.order.OrderRequest;
import com.rms.feasty.dto.order.OrderResponse;
import com.rms.feasty.entity.Order;
import com.rms.feasty.entity.RestaurantTable;
import com.rms.feasty.repository.ItemRepository;
import com.rms.feasty.repository.OrderItemRepository;
import com.rms.feasty.repository.OrderRepository;
import com.rms.feasty.repository.TableRepository;
import com.rms.feasty.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ExtendWith(SpringExtension.class)
public class TestOrderService {

    @Mock
    OrderRepository orderRepository;

    @Mock
    OrderItemRepository orderItemRepository;

    @Mock
    ItemRepository itemRepository;

    @Mock
    TableRepository tableRepository;

    @InjectMocks
    OrderServiceImpl orderService;

    @Test
    void testAddOrder_nullParameter() {
        // mock
        OrderRequest mockOrderRequest = null;

        // act
        OrderResponse mockOrderResponse = orderService.addOrder(mockOrderRequest);

        // assert
        Assertions.assertNull(mockOrderResponse);
    }

    @Test
    void testAddOrder_orderSaved() {
        // mock
        int mockTableId = 1;
        int mockCustomerId = 1;
        int mockOrderId = 1;

        OrderRequest mockOrderRequest = OrderRequest.builder()
                .tableId(mockTableId)
                .customerId(mockCustomerId)
                .build();

        RestaurantTable mockTable = new RestaurantTable(1, 1);

        Order mockOrder = new Order();
        mockOrder.setOrderTable(mockTable);
        mockOrder.setStatus(OrderStatusEnum.OPENED);

        Order mockOrderWithId = new Order();
        mockOrderWithId.setOrderTable(mockTable);
        mockOrderWithId.setStatus(mockOrder.getStatus());
        mockOrderWithId.setId(mockOrderId);

        Mockito.when(tableRepository.getReferenceById(mockTableId)).thenReturn(mockTable);
        Mockito.when(orderRepository.save(mockOrder)).thenReturn(mockOrderWithId);

        OrderResponse mockOrderResponse = OrderResponse.builder()
                .orderId(mockOrderId)
                .customerId(mockCustomerId)
                .tableId(mockTableId)
                .status(OrderStatusEnum.OPENED.toString())
                .build();

        // act
        OrderResponse response = orderService.addOrder(mockOrderRequest);

        // assert
        Assertions.assertEquals(mockOrderResponse, response);
    }

    @Test
    void testAddOrder_onExceptionReturnNull() {
        // mock
        // act
        // assert
    }

    @Test
    void testGetAllOpenOrders() {
        // mock
//        List<Order> mockOrders = new ArrayList<>();
//        mockOrders.add(new Order());
//        mockOrders.add(new Order());
//
//        Mockito.when(orderRepository.findAllByStatus(OrderStatusEnum.OPENED, Sort.by(Sort.Order.asc("id")))).thenReturn(mockOrders);
//
//        List<OrderResponse> mockOrderResponse = new ArrayList<>();
//
//        // act
//        List<OrderResponse> result = orderService.addOrder(mockOrderRequest);
//
//        // assert
//        Assertions.assertEquals(result, mockOrderResponse);
    }

    @Test
    void testAddItemsToOrder() {
        // mock
        // act
        // assert
    }

    @Test
    void testGetOrderDetailsById() {
        // mock
        // act
        // assert
    }
}
