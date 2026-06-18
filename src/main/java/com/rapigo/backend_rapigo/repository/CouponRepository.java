package com.rapigo.backend_rapigo.repository;

import com.rapigo.backend_rapigo.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, String> {
}
