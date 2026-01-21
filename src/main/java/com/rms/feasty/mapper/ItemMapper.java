/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.mapper;

import com.rms.feasty.dto.item.ItemResponse;
import com.rms.feasty.entity.Item;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ItemMapper {
    public static ItemResponse buildItemResponse(Item item) {
        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setItemId(item.getId());
        itemResponse.setName(item.getName());
        itemResponse.setDescription(item.getDescription());
        itemResponse.setPrice(item.getCost());
        itemResponse.setStatus(item.getStatus().getStatus());
        return itemResponse;
    }

    public static List<ItemResponse> buildItemResponse(List<Item> items) {
        if (CollectionUtils.isEmpty(items)) {
            return new ArrayList<>();
        }

        List<ItemResponse> itemResponses = new ArrayList<>();
        items.forEach(item -> {
            itemResponses.add(buildItemResponse(item));
        });
        return itemResponses;
    }

}
