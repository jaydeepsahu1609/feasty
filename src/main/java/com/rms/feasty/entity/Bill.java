/*
 * @Copyright 2026, Jaydeep Sahu
 */

package com.rms.feasty.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
}
