/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderItemStatusEnum {
    PENDING(0, "PENDING"), // to be acknowledged by chef; may be the chef is busy right now
    PREPARING(1, "PREPARING"), // chef has acknowledged the item; cooking is in progress
    READY(2, "READY"), // item has been cooked; waiting to be picked up by the waiter
    SERVED(3, "SERVED"), // item has been served to the customer
    CANCELLED(4, "CANCELLED"); // either chef or the customer has canceled the order for some reason

    private final int statusCode;
    private final String label;

}
