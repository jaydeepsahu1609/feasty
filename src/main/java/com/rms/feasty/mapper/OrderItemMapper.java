/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.mapper;

import com.rms.feasty.dto.order.OrderItemResponse;
import com.rms.feasty.entity.OrderItem;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderItemMapper {
    public static OrderItemResponse buildOrderItemResponse(OrderItem orderItem) {
        OrderItemResponse orderItemResponse = new OrderItemResponse();
        orderItemResponse.setId(orderItem.getId());
        orderItemResponse.setOrderId(orderItem.getOrder().getId());
        orderItemResponse.setItemId(orderItem.getItem().getId());
        orderItemResponse.setCount(orderItem.getCount());
        orderItemResponse.setPendingItems(orderItem.getPendingCount());
        orderItemResponse.setStatus(orderItem.getStatus().getLabel());
        return orderItemResponse;
    }

    public static List<OrderItemResponse> buildOrderItemResponse(List<OrderItem> orders) {
        if (CollectionUtils.isEmpty(orders)) {
            return new ArrayList<>();
        }

        List<OrderItemResponse> orderItemResponses = new ArrayList<>();
        orders.forEach(order -> {
            orderItemResponses.add(buildOrderItemResponse(order));
        });
        return orderItemResponses;
    }

}
