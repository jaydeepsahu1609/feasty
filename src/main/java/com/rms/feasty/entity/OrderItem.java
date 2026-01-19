/*
 * @Copyright 2026, Jaydeep Sahu
 */

package com.rms.feasty.entity;

import com.rms.feasty.constants.OrderItemStatusEnum;
import jakarta.persistence.*;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private int count; // quantity of items served to the customer so far (doesn't include pending)

    private int pendingCount; // quantity of items to be served to customer

    private OrderItemStatusEnum status;

}
