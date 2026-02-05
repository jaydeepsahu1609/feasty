/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.dto.order;

import com.rms.feasty.constants.OrderStatusEnum;
import lombok.Data;

import java.util.List;

@Data
public class DetailedOrderResponse {
    // order related details
    private Integer orderId;
    private Integer tableId;
    private String status = OrderStatusEnum.OPENED.getLabel();
    private Integer customerId;
    private float total;

    // order-items
    private List<DetailedOrderItemResponse> orderItems;
}

