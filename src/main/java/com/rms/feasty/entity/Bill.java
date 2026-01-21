/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.entity;

import jakarta.persistence.*;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
}
