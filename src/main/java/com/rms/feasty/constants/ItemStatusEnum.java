/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemStatusEnum {
    OUT_OF_STOCK(0, "OUT OF STOCK"), // no further request should be acknowledged
    IN_STOCK(1, "IN STOCK"); // item is in stock

    private final int statusCode;
    private final String status;
}
