/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.service;

import com.rms.feasty.constants.ItemStatusEnum;
import com.rms.feasty.entity.Item;
import com.rms.feasty.repository.ItemRepository;
import com.rms.feasty.service.impl.ItemServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TestItemService {

    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    ItemServiceImpl itemService;

    @Test
    void testGetItemsByIds_returnsEmptyListWhenIdsNullOrEmpty() {
        // mock
        List<Item> mockItems = new ArrayList<>();

        // act
        List<Item> result = itemService.getItemsByIds(Collections.emptyList());

        // assert
        Assertions.assertEquals(mockItems, result);
    }

    @Test
    void testGetItemsByIds_returnsListWhenIdsExist() {
        // mock
        List<Integer> mockIds = List.of(1, 2);

        List<Item> mockItems = new ArrayList<>();
        mockItems.add(new Item(mockIds.get(0), "mock-1", "mock description", 0.0f, ItemStatusEnum.IN_STOCK));
        mockItems.add(new Item(mockIds.get(1), "mock-2", "mock description", 0.0f, ItemStatusEnum.IN_STOCK));


        Mockito.when(itemRepository.findAllById(mockIds)).thenReturn(mockItems);

        // act
        List<Item> result = itemService.getItemsByIds(mockIds);

        Assertions.assertEquals(mockItems, result);
    }
}
