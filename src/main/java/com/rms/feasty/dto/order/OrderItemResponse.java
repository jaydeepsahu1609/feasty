/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.dto.order;

import lombok.Data;

@Data
public class OrderItemResponse {
    private int id;
    private int orderId;
    private int itemId;
    private int count;
    private int pendingItems;
    private String status;
}
