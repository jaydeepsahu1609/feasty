/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.dto.order;

import com.rms.feasty.constants.OrderItemStatusEnum;
import com.rms.feasty.constants.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDetailRow {
    // order related details
    private Integer orderId;
    private Integer tableId;
    private OrderStatusEnum status;
    private Integer customerId;

    // order-item related details
    private Integer orderItemid;
    private Integer count;
    private Integer pendingItems;
    private OrderItemStatusEnum orderItemstatus;

    // item related details
    private Integer itemId;
    private String name;
    private String description;
    private Float cost;
}
