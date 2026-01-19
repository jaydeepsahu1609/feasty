/*
 * @Copyright 2026, Jaydeep Sahu
 */

package com.rms.feasty.controller;

import com.rms.feasty.entity.Item;
import com.rms.feasty.repository.ItemRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/")
    public List<Item> getAllItems(){
        // TODO: add pagination
        logger.debug("Entry: getAllItems");

        List<Item> items = itemRepository.findAll(Sort.by(Sort.Order.asc("id")));

        logger.debug("Returning {} items", items.size());
        logger.debug("Exit: getAllItems");

        return items;
    }

}
