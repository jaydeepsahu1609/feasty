/*
 * @Copyright 2026, Jaydeep Sahu
 */

package com.rms.feasty.controller;

import com.rms.feasty.entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class ItemsController {
    Logger logger = LogManager.getLogger(ItemsController.class);

    @PostMapping("/")
    public Order initiateOrder(@RequestBody Order order){
        logger.debug("Entry: initiateOrder");

        logger.debug("{}", order);

        logger.debug("Exit: initiateOrder");

        return order;
    }

}
