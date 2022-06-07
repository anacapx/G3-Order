package com.g3.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g3.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
