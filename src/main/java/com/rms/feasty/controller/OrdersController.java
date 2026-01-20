/*
 * @Copyright 2026, Jaydeep Sahu
 */

package com.rms.feasty.controller;

import com.rms.feasty.entity.Order;
import com.rms.feasty.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Tag(name = "Orders", description = "APIs related to restaurant orders")
public class OrdersController {

    private static final Logger logger = LogManager.getLogger(OrdersController.class);

    private final OrderService orderService;

    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Initiate a new order")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Order created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid order payload"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<Order> initiateOrder(@RequestBody Order order) {
        logger.debug("Inside: initiateOrder");

        Order savedOrder = orderService.addOrder(order);

        if (savedOrder == null) {
            logger.error("Order initiation failed.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    @Operation(summary = "Get all OPEN orders")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Fetched open orders successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/open")
    public ResponseEntity<List<Order>> getAllOpenOrders() {
        logger.debug("Inside: getAllOpenOrders");

        List<Order> orders = orderService.getAllOpenOrders();
        return ResponseEntity.ok(orders);
    }
}
