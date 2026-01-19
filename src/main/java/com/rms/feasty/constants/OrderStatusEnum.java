/*
 * @Copyright 2026, Jaydeep Sahu
 */

package com.rms.feasty.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum {
    OPENED(0), // bill is opened
    CLOSED(1); // bill is either paid or canceled

    private final int status;

}
