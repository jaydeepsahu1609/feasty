/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.dto.order;

import com.rms.feasty.constants.OrderStatusEnum;
import lombok.Data;

@Data
public class OrderResponse {
    private Integer orderId;
    private Integer tableId;
    private String status = OrderStatusEnum.OPENED.getLabel();
    private Integer customerId;

    // private List<OrderItemResponse> orderItems
}

