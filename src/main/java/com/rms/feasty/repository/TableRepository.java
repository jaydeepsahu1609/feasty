/*
 * @Copyright 2026, Jaydeep Sahu
 */

package com.rms.feasty.repository;

import com.rms.feasty.entity.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<RestaurantTable, Integer> {
}
