/*
 * @Copyright 2026, Jaydeep Sahu
 */

package com.rms.feasty.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemStatusEnum {
    OUT_OF_STOCK(0), // no further request should be acknowledged
    IN_STOCK(1); // item is in stock

    private final int status;

}
