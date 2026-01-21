/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.mapper;

import com.rms.feasty.dto.order.OrderResponse;
import com.rms.feasty.entity.Order;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderMapper {
    public static OrderResponse buildOrderResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(order.getId());
        orderResponse.setCustomerId(Objects.nonNull(order.getCustomer()) ? order.getCustomer().getId() : null);
        orderResponse.setTableId(Objects.nonNull(order.getOrderTable()) ? order.getOrderTable().getId() : null);
        orderResponse.setStatus(order.getStatus().getLabel());
        return orderResponse;
    }

    public static List<OrderResponse> buildOrderResponse(List<Order> orders) {
        if (CollectionUtils.isEmpty(orders)) {
            return new ArrayList<>();
        }

        List<OrderResponse> orderResponses = new ArrayList<>();
        orders.forEach(order -> {
            orderResponses.add(buildOrderResponse(order));
        });
        return orderResponses;
    }

}
