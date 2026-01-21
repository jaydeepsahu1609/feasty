/*
 * @Copyright 2026, Jaydeep Sahu
 */

package com.rms.feasty.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum {
    OPENED(0, "OPENED"), // bill is opened
    CLOSED(1, "CLOSED"); // bill is either paid or canceled

    private final int statusCode;
    private final String label;

    public static OrderStatusEnum getFromStatusCode(int statusCode) {
        for(OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
            if(orderStatusEnum.getStatusCode() == statusCode) {
                return orderStatusEnum;
            }
        }
        return null;
    }

    public static OrderStatusEnum getFromStatusCode(String status) {
        for(OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
            if(orderStatusEnum.getLabel().equals(status)) {
                return orderStatusEnum;
            }
        }
        return OPENED; // default to open
    }
}
