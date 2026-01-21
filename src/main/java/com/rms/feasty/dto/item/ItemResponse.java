/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.dto.item;

import lombok.Data;

@Data
public class ItemResponse {
    private Integer itemId;
    private String name;
    private String description;
    private Float price;
    private String status;
}
