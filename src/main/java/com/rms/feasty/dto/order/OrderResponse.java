/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.dto.order;

import com.rms.feasty.constants.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Integer orderId;
    private Integer tableId;

    @Builder.Default
    private String status = OrderStatusEnum.OPENED.getLabel();

    private Integer customerId;

    // private List<OrderItemResponse> orderItems
}

