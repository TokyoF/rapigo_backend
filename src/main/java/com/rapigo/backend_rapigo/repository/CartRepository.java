package com.rapigo.backend_rapigo.repository;

import com.rapigo.backend_rapigo.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
