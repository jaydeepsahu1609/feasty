/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.dto.order;

import lombok.Data;

@Data
public class OrderItemRequest {
    private int orderId;
    private int itemId;
    private int quantity;
}
