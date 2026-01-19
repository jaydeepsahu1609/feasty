/*
 * @Copyright 2026, Jaydeep Sahu
 */

package com.rms.feasty.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
}
