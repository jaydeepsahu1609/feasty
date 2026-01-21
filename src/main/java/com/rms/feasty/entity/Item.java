/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rms.feasty.constants.ItemStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "items")
@ToString
@Getter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String name;

    private String description;

    private double cost;

    private ItemStatusEnum status; // in stock; out of stock

    @JsonIgnore
    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems; // one item may be requested by many customer in a day; hence it belongs to many orderItems

    protected Item() {
        // no args constructor is must for an entity
    }

    public Item(String name, String description, double cost, ItemStatusEnum status) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.status = status;
    }
}
