/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.util;

import com.rms.feasty.constants.ItemStatusEnum;
import com.rms.feasty.entity.Item;
import com.rms.feasty.entity.RestaurantTable;
import com.rms.feasty.repository.ItemRepository;
import com.rms.feasty.repository.TableRepository;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AutoPopulateTables {
    private static final Logger logger = LogManager.getLogger(AutoPopulateTables.class);
    private final TableRepository tableRepository;

    private final List<RestaurantTable> tables = List.of(
            new RestaurantTable(2),
            new RestaurantTable(2),
            new RestaurantTable(2),
            new RestaurantTable(2),
            new RestaurantTable(3),
            new RestaurantTable(3),
            new RestaurantTable(3),
            new RestaurantTable(4),
            new RestaurantTable(4),
            new RestaurantTable(4),
            new RestaurantTable(4),
            new RestaurantTable(8),
            new RestaurantTable(8)
    );

    public AutoPopulateTables(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @PostConstruct
    void insertTablesInDBTable() {
        try {
            tableRepository.saveAll(tables);
            logger.debug("{} tables added to RestaurantTable table.", tables.size());
        } catch (Exception e) {
            logger.error("Could not insert items in the RestaurantTable table - {}", e.getMessage());
        }
    }
}

