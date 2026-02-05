/*
 * Copyright 2026 Jaydeep Sahu
 * Practice / learning project
 */

package com.rms.feasty.repository;

import com.rms.feasty.constants.OrderStatusEnum;
import com.rms.feasty.dto.order.OrderDetailRow;
import com.rms.feasty.entity.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @EntityGraph(attributePaths = {"orderTable", "orderItems", "orderItems.item"})
    List<Order> findAllByStatus(OrderStatusEnum status, Sort sort);

    /**
     * Returns detailed order from DB.
     *
     * @return {@link List} of {@link OrderDetailRow} for the given orderId
     *
     */
    @Query("SELECT new com.rms.feasty.dto.order.OrderDetailRow(o.id, o.orderTable.id, o.status, " +
            "o.customer.id, oi.id, oi.count, oi.pendingCount, oi.status, " +
            "i.id, i.name, i.description, i.cost" +
            ") FROM Order o " +
            "LEFT JOIN OrderItem oi ON o.id = oi.order.id " +
            "LEFT JOIN Item i on oi.item.id = i.id " +
            "WHERE o.id = :orderId")
    List<OrderDetailRow> getDetailedOrderById(int orderId);
}
