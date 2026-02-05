/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.service;

import com.rms.feasty.dto.order.*;
import com.rms.feasty.exceptions.ItemNotFoundException;
import com.rms.feasty.exceptions.OrderNotFoundException;

import java.util.List;

public interface OrderService {
    OrderResponse addOrder(OrderRequest orderRequest);

    List<OrderResponse> getAllOpenOrders();

    List<OrderItemResponse> addItemsToOrder(int orderId, List<OrderItemRequest> orderItems) throws OrderNotFoundException, ItemNotFoundException;

    DetailedOrderResponse getOrderDetailsById(int orderId) throws OrderNotFoundException;
}
