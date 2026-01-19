/*
 * @Copyright 2026, Jaydeep Sahu
 */

package com.rms.feasty.repository;

import com.rms.feasty.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
}
