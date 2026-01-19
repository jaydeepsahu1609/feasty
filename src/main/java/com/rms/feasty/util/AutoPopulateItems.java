/*
 * @Copyright 2026, Jaydeep Sahu
 */

package com.rms.feasty.util;

import com.rms.feasty.constants.ItemStatusEnum;
import com.rms.feasty.entity.Item;
import com.rms.feasty.repository.ItemRepository;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AutoPopulateItems {
    private static final Logger logger = LogManager.getLogger(AutoPopulateItems.class);
    private final ItemRepository itemRepository;

    public AutoPopulateItems(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    private final List<Item> items = List.of(// --- Non-Vegetarian Favorites ---
            new Item("Butter Chicken", "Tender chicken pieces simmered in a creamy, mildly spiced tomato and cashew nut gravy.", 380, ItemStatusEnum.IN_STOCK),
            new Item("Chicken Tikka Masala", "Grilled chicken chunks cooked in a spicy, thick onion-tomato gravy with bell peppers.", 360, ItemStatusEnum.IN_STOCK),
            new Item("Mutton Rogan Josh", "A classic Kashmiri delicacy of slow-cooked lamb in a rich gravy of ginger, garlic, and aromatic spices.", 450, ItemStatusEnum.IN_STOCK),
            new Item("Chicken Biryani", "Aromatic long-grain basmati rice cooked with succulent chicken and traditional pot-sealed spices.", 320, ItemStatusEnum.IN_STOCK),

            // --- Dals and Rice ---
            new Item("Jeera Rice", "Fragrant basmati rice tempered with cumin seeds and ghee", 140, ItemStatusEnum.IN_STOCK),
            new Item("Dal Tadka", "Yellow lentils cooked with spices and tempered with ghee and red chilies", 180, ItemStatusEnum.IN_STOCK),
            new Item("Dal Makhani", "Slow-cooked black lentils and kidney beans in a creamy, buttery tomato sauce", 240, ItemStatusEnum.IN_STOCK),
            new Item("Veg Biryani", "Fragrant basmati rice layered with seasonal vegetables and aromatic spices", 220, ItemStatusEnum.IN_STOCK),

            // --- Popular Vegetarian Mains & Starters ---
            new Item("Paneer Butter Masala", "Cubes of paneer cooked in a rich, creamy tomato and butter gravy.", 280, ItemStatusEnum.IN_STOCK),
            new Item("Kadai Paneer", "Paneer tossed with bell peppers and onions in a spicy tomato-based masala", 270, ItemStatusEnum.IN_STOCK),
            new Item("Chana Masala", "Soft chickpeas cooked in a tangy and spicy North Indian style onion-tomato masala.", 220, ItemStatusEnum.IN_STOCK),
            new Item("Veg Manchurian", "Crispy vegetable dumplings tossed in a tangy, spicy Indo-Chinese ginger-garlic sauce.", 240, ItemStatusEnum.IN_STOCK),
            new Item("Gobi Manchurian", "Batter-fried cauliflower florets sautéed with green chilies, soy sauce, and spring onions.", 230, ItemStatusEnum.IN_STOCK),
            new Item("Paneer Tikka", "Marinated paneer cubes grilled in a tandoor with onions and bell peppers", 260, ItemStatusEnum.IN_STOCK),
            new Item("Malai Kofta", "Soft potato and paneer dumplings simmered in a mild, velvety cashew gravy", 290, ItemStatusEnum.IN_STOCK),
            new Item("Mix Vegetable", "A seasonal medley of vegetables sautéed in a semi-dry onion-tomato masala", 210, ItemStatusEnum.IN_STOCK),

            // --- South Indian Specialties ---
            new Item("Masala Dosa", "Crispy rice and lentil crepe stuffed with a tempered potato mash, served with sambar and chutney.", 180, ItemStatusEnum.IN_STOCK),
            new Item("Idli Sambar", "Soft, fluffy steamed rice cakes served with a flavorful lentil-based vegetable stew.", 120, ItemStatusEnum.IN_STOCK),

            // --- Breads & Sides ---
            new Item("Lachha Paratha", "Multi-layered, flaky whole wheat bread cooked on a griddle with ghee.", 50, ItemStatusEnum.IN_STOCK),
            new Item("Tandoori Roti", "Traditional whole wheat bread baked in a clay oven", 25, ItemStatusEnum.IN_STOCK),
            new Item("Butter Chapati", "Whole wheat flatbread cooked on a griddle and brushed with melted butter", 20, ItemStatusEnum.IN_STOCK),
            new Item("Garlic Naan", "Soft, leavened bread topped with minced garlic and fresh coriander", 75, ItemStatusEnum.IN_STOCK),
            new Item("Aloo Paratha", "Griddle-fried flatbread stuffed with a spiced mashed potato filling", 60, ItemStatusEnum.IN_STOCK),
            new Item("Mixed Veg Raita", "Cooling whisked yogurt mixed with finely chopped cucumber, tomatoes, and onions.", 90, ItemStatusEnum.IN_STOCK),

            // --- Beverages & Desserts ---
            new Item("Mango Lassi", "A refreshing, thick yogurt-based drink blended with sweet mango pulp.", 110, ItemStatusEnum.IN_STOCK),
            new Item("Rasmalai", "Soft cottage cheese patties soaked in chilled, saffron-infused sweetened milk (2 pieces).", 120, ItemStatusEnum.IN_STOCK),
            new Item("Gajar Halwa", "Traditional warm carrot pudding enriched with khoya, nuts, and cardamom.", 140, ItemStatusEnum.IN_STOCK));

    @PostConstruct
    void insertItemsInDBTable() {
        try {
            itemRepository.saveAll(items);
            logger.debug("{} items added to Items table.", items.size());
        } catch (Exception e) {
            logger.error("Could not insert items in the Item table - {}", e.getMessage());
        }
    }
}

