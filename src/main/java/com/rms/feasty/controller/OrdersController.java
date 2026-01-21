/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.controller;

import com.rms.feasty.dto.order.OrderRequest;
import com.rms.feasty.dto.order.OrderResponse;
import com.rms.feasty.entity.Order;
import com.rms.feasty.entity.OrderItem;
import com.rms.feasty.exceptions.ItemNotFoundException;
import com.rms.feasty.exceptions.OrderNotFoundException;
import com.rms.feasty.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public ResponseEntity<OrderResponse> initiateOrder(@RequestBody OrderRequest order) {
        logger.debug("Inside: initiateOrder");

        OrderResponse savedOrder = orderService.addOrder(order);

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
    public ResponseEntity<List<OrderResponse>> getAllOpenOrders() {
        logger.debug("Inside: getAllOpenOrders");

        List<OrderResponse> orders = orderService.getAllOpenOrders();
        return ResponseEntity.ok(orders);
    }

//    @Operation(summary = "Add item to an order")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "Order updated successfully"),
//            @ApiResponse(responseCode = "404", description = "Order not found in DB"),
//            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
//    })
//    @PostMapping("/{orderId}/items")
//    public ResponseEntity<List<OrderItem>> addItemsToOrder(@PathVariable int orderId, @RequestBody List<OrderItem> orderItems) {
//        logger.debug("Inside: addItemsToOrder");
//
//        try {
//            List<OrderItem> updatedOrderItems = orderService.addItemsToOrder(orderId, orderItems);
//            return ResponseEntity.ok(updatedOrderItems);
//        } catch (Exception e) {
//            if (e instanceof OrderNotFoundException || e instanceof ItemNotFoundException)
//                return ResponseEntity.status(404).build();
//            return ResponseEntity.status(500).build();
//        }
//    }
}
