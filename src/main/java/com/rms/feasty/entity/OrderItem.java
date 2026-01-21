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
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    @Setter
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @Setter
    private Item item;

    private int count; // quantity of items served to the customer so far (doesn't include pending)

    @Setter
    private int pendingCount; // quantity of items to be served to customer

    @Setter
    private OrderItemStatusEnum status = OrderItemStatusEnum.PENDING;

}
