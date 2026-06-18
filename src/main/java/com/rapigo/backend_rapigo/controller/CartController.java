package com.rapigo.backend_rapigo.controller;

import com.rapigo.backend_rapigo.dto.AddItemRequest;
import com.rapigo.backend_rapigo.dto.CartResponse;
import com.rapigo.backend_rapigo.dto.CouponRequest;
import com.rapigo.backend_rapigo.model.Cart;
import com.rapigo.backend_rapigo.model.Order;
import com.rapigo.backend_rapigo.service.CartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/cart")
    public Cart createCart() {
        return cartService.createCart();
    }

    @GetMapping("/cart/{id}")
    public CartResponse getCart(@PathVariable Long id) {
        return cartService.getCart(id);
    }

    @PostMapping("/cart/{id}/items")
    public CartResponse addItem(@PathVariable Long id, @RequestBody AddItemRequest body) {
        return cartService.addItem(id, body.getMenuItemId(), body.getQty());
    }

    @PostMapping("/cart/{id}/coupon")
    public CartResponse applyCoupon(@PathVariable Long id, @RequestBody CouponRequest body) {
        return cartService.applyCoupon(id, body.getCode());
    }

    @PostMapping("/cart/{id}/checkout")
    public Order checkout(@PathVariable Long id) {
        return cartService.checkout(id);
    }
}
