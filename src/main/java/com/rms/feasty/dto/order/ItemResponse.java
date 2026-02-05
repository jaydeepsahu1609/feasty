/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.dto.order;

import lombok.Data;

@Data
public class ItemResponse {
    private int itemId;
    private String name;
    private String description;
    private float cost;
}
