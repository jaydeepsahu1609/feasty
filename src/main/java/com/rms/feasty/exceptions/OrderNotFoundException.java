/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.exceptions;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(){}

    public OrderNotFoundException(String message){
        super(message);
    }
}
