/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.service;

import com.rms.feasty.dto.order.OrderItemRequest;
import com.rms.feasty.dto.order.OrderItemResponse;
import com.rms.feasty.dto.order.OrderRequest;
import com.rms.feasty.dto.order.OrderResponse;
import com.rms.feasty.entity.Order;
import com.rms.feasty.entity.OrderItem;
import com.rms.feasty.exceptions.ItemNotFoundException;
import com.rms.feasty.exceptions.OrderNotFoundException;

import java.util.List;

public interface OrderService {
    OrderResponse addOrder(OrderRequest orderRequest);

    List<OrderResponse> getAllOpenOrders();

    List<OrderItemResponse> addItemsToOrder(int orderId, List<OrderItemRequest> orderItems) throws OrderNotFoundException, ItemNotFoundException;
}
