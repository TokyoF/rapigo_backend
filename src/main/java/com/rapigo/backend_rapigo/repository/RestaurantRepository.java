package com.rapigo.backend_rapigo.repository;

import com.rapigo.backend_rapigo.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
