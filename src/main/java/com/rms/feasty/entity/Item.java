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
import java.util.Objects;

@Entity
@Table(name = "items")
@ToString
@Getter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;

    private String description;

    private float cost;

    private ItemStatusEnum status; // in stock; out of stock

    @JsonIgnore
    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems; // one item may be requested by many customer in a day; hence it belongs to many orderItems

    protected Item() {
        // no args constructor is must for an entity
    }

    public Item(String name, String description, float cost, ItemStatusEnum status) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.status = status;
    }

    public Item(int id, String name, String description, float cost, ItemStatusEnum status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.status = status;
    }

    // Proper equals & hashCode for JPA entity
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
