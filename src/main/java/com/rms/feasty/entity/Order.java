/*
 * @Copyright 2026, Jaydeep Sahu
 */

package com.rms.feasty.entity;

import com.rms.feasty.constants.OrderStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "orders")
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private RestaurantTable orderTable;

    private OrderStatusEnum status; // open, closed

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @ManyToOne(fetch = FetchType.LAZY) // rarely needed as of now
    @JoinColumn(name = "customer_id")
    Customer customer; // one order belongs to only one customer

}
