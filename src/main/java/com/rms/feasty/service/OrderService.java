/*
 * @Copyright 2026, Jaydeep Sahu
 */

package com.rms.feasty.service;

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

//    List<OrderItem> addItemsToOrder(int orderId, List<OrderItem> orderItems) throws OrderNotFoundException, ItemNotFoundException;
}
