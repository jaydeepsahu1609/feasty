/*
 * @Copyright 2026, Jaydeep Sahu
 */

package com.rms.feasty.entity;

import com.rms.feasty.constants.OrderStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
    private RestaurantTable orderTable;

    private OrderStatusEnum status = OrderStatusEnum.OPENED; // open (default), closed

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @ManyToOne(fetch = FetchType.LAZY) // rarely needed as of now
    @JoinColumn(name = "customer_id")
    Customer customer; // one order belongs to only one customer

}
