package com.rapigo.backend_rapigo.repository;

import com.rapigo.backend_rapigo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
