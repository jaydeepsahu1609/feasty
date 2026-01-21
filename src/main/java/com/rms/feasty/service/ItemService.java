/*
 * @Copyright 2026, Jaydeep Sahu
 */

package com.rms.feasty.service;

import com.rms.feasty.entity.Item;

import java.util.List;

public interface ItemService {
    List<Item> getItemsByIds(List<Integer> ids);
}
