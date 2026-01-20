/*
 * @Copyright 2026, Jaydeep Sahu
 */

package com.rms.feasty.service.impl;

import com.rms.feasty.constants.OrderStatusEnum;
import com.rms.feasty.entity.Order;
import com.rms.feasty.repository.OrderRepository;
import com.rms.feasty.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final static Logger logger = LogManager.getLogger(OrderServiceImpl.class);
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public Order addOrder(Order order) {
        logger.debug("Inside: addOrder");
        if(order == null){
            logger.warn("Null order. Returning null.");
            return null;
        }

        Order savedOrder = null;

        try {
            savedOrder = orderRepository.save(order);
            logger.info("Order {} initiated successfully.", savedOrder.getId());
        }catch (Exception e){
            logger.error("Error while initiating the order. Reason- {}", e.getMessage());
        }
        return savedOrder;
    }

    @Override
    public List<Order> getAllOpenOrders() {
        logger.debug("Inside: getAllOpenOrders");

        List<Order> orders = orderRepository.findAllByStatus(OrderStatusEnum.OPENED, Sort.by(Sort.Order.asc("id")));

        logger.debug("Found {} open orders", orders.size());

        return orders;
    }


}
