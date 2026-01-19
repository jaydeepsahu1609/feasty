/*
 * @Copyright 2026, Jaydeep Sahu
 */

package com.rms.feasty.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderItemStatusEnum {
    PENDING(0), // to be acknowledged by chef; may be the chef is busy right now
    PREPARING(1), // chef has acknowledged the item; cooking is in progress
    READY(2), // item has been cooked; waiting to be picked up by the waiter
    SERVED(3), // item has been served to the customer
    CANCELLED(4); // either chef or the customer has canceled the order for some reason

    private final int status;

}
