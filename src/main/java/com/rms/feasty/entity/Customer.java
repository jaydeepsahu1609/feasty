/*
 * @Copyright 2026, Jaydeep Sahu
 */

package com.rms.feasty.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    private String email;

    private String contact;

    @OneToMany(mappedBy = "customer")
    List<Order> orders; // one registered customer can have many orders
}
