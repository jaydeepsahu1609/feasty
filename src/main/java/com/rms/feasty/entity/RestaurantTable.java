/*
 * @Copyright 2026, Jaydeep Sahu
 */

package com.rms.feasty.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private int capacity;

    @OneToMany(mappedBy = "orderTable")
    List<Order> orders; // one table, many orders in a day
}
