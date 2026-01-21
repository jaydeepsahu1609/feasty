/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private Integer capacity;

    @OneToMany(mappedBy = "orderTable")
    @JsonIgnore
    List<Order> orders; // one table, many orders in a day

    public RestaurantTable(int capacity){
        this.capacity = capacity;
    }
    public RestaurantTable(int id, Integer capacity) {
        this.capacity = id;
    }
}
