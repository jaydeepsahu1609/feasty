/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.service.impl;

import com.rms.feasty.entity.Item;
import com.rms.feasty.repository.ItemRepository;
import com.rms.feasty.service.ItemService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final static Logger logger = LogManager.getLogger(ItemServiceImpl.class);

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    @Cacheable(value = "items", key = "#id")
    public List<Item> getItemsByIds(List<Integer> ids) {
        logger.debug("Inside: getItemsByIds");

        if(ids.isEmpty())
            return Collections.emptyList();

        return itemRepository.findAllById(ids);
    }
}
