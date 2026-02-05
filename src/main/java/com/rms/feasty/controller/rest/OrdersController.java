/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.controller.rest;

import com.rms.feasty.dto.order.*;
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
            return ResponseEntity.internalServerError().build();
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

    @Operation(summary = "Add items to an order")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order updated successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found in DB"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @PostMapping("/{orderId}/items")
    public ResponseEntity<List<OrderItemResponse>> addItemsToOrder(@PathVariable int orderId, @RequestBody List<OrderItemRequest> orderItems) {
        logger.debug("Inside: addItemsToOrder");

        try {
            List<OrderItemResponse> updatedOrderItems = orderService.addItemsToOrder(orderId, orderItems);
            return ResponseEntity.ok(updatedOrderItems);
        } catch (Exception e) {
            if (e instanceof OrderNotFoundException || e instanceof ItemNotFoundException)
                return ResponseEntity.notFound().build();
            return ResponseEntity.internalServerError().build();
        }
    }

    // get order details by ID
    // params: id
    // DTO: DetailedOrderResponse
    @Operation(summary = "Get order details")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{orderId}")
    public ResponseEntity<DetailedOrderResponse> getOrderDetailsById(@PathVariable int orderId) {
        logger.debug("Inside: getOrderDetailsById- {}", orderId);

        try {
            DetailedOrderResponse orderResponses = orderService.getOrderDetailsById(orderId);
            return ResponseEntity.ok(orderResponses);
        } catch (Exception e) {
            logger.error("Error while fetching details for order-{} : {}", orderId, e);
            if (e instanceof OrderNotFoundException)
                return ResponseEntity.notFound().build();
            return ResponseEntity.internalServerError().build();
        }
    }

}
