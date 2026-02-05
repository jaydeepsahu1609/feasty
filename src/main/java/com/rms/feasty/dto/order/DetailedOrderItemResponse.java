/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.dto.order;

import lombok.Data;

@Data
public class DetailedOrderItemResponse {
    // order related details
    private int id;
    private int orderId;
    private int itemsCount;
    private int pendingItems;
    private String status;

    // item related details
    private ItemResponse item;
}
