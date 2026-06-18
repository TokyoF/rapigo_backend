package com.rapigo.backend_rapigo;

import com.rapigo.backend_rapigo.model.Cart;
import com.rapigo.backend_rapigo.model.Order;
import com.rapigo.backend_rapigo.model.Restaurant;
import com.rapigo.backend_rapigo.dto.CartResponse;
import com.rapigo.backend_rapigo.service.CartService;
import com.rapigo.backend_rapigo.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RapigoBugTests {

    @Autowired
    CartService cartService;

    @Autowired
    RestaurantService restaurantService;

    @Test
    void cuponNoSeAcumula() {
        Cart cart = cartService.createCart();
        cartService.addItem(cart.getId(), 1L, 2);          // subtotal = 100
        cartService.applyCoupon(cart.getId(), "DELI10");
        CartResponse res = cartService.applyCoupon(cart.getId(), "DELI10"); // aplicado DOS veces
        assertEquals(10.0, res.getDiscount(), 0.001);      // un solo 10%, NO 20 (falla por BUG RG-204)
    }

    @Test
    void totalIncluyeEnvio() {
        Cart cart = cartService.createCart();
        cartService.addItem(cart.getId(), 1L, 2);          // subtotal = 100, sin cupón
        Order order = cartService.checkout(cart.getId());
        assertEquals(105.0, order.getTotal(), 0.001);      // 100 + 5 envío (falla por BUG RG-205)
    }

    @Test
    void filtroCocinaIgnoraMayusculas() {
        List<Restaurant> r = restaurantService.list("pizza"); // minúscula
        assertEquals(1, r.size());
        assertEquals("Pizzería Napoli", r.get(0).getName());   // falla por BUG RG-207 (lista vacía)
    }
}
