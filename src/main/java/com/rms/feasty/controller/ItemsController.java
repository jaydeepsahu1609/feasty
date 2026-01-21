/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.controller;

import com.rms.feasty.dto.item.ItemResponse;
import com.rms.feasty.entity.Item;
import com.rms.feasty.mapper.ItemMapper;
import com.rms.feasty.repository.ItemRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemsController {
    Logger logger = LogManager.getLogger(ItemsController.class);

    private final ItemRepository itemRepository;

    public ItemsController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Operation(summary = "Get all items from inventory")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Fetched items successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/")
    public ResponseEntity<List<ItemResponse>> getAllItems() {
        logger.debug("Entry: getAllItems");

        List<Item> items = itemRepository.findAll(Sort.by(Sort.Order.asc("id")));
        logger.debug("Returning {} items", items.size());

        List<ItemResponse> itemResponseList = ItemMapper.buildItemResponse(items);
        return ResponseEntity.ok(itemResponseList);
    }

}
