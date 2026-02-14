/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.service;

import com.rms.feasty.dto.order.OrderRequest;
import com.rms.feasty.dto.order.OrderResponse;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
    void testGetAllOpenOrders() {
        // mock
        // act
        // assert
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
