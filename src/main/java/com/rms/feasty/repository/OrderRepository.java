/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.repository;

import com.rms.feasty.constants.OrderStatusEnum;
import com.rms.feasty.entity.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @EntityGraph(attributePaths = {"orderTable", "orderItems", "orderItems.item"})
    List<Order> findAllByStatus(OrderStatusEnum status, Sort sort);
}
