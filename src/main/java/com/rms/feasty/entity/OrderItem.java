/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rms.feasty.constants.OrderItemStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore

    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")

    private Item item;

    private int count; // quantity of items served to the customer so far (doesn't include pending)

    private int pendingCount; // quantity of items to be served to customer

    private OrderItemStatusEnum status = OrderItemStatusEnum.PENDING;

}
