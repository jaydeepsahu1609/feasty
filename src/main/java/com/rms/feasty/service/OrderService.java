/*
 * @Copyright 2026, Jaydeep Sahu
 */

package com.rms.feasty.service;

import com.rms.feasty.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService {
    Order addOrder(Order order);

    List<Order> getAllOpenOrders();
}
