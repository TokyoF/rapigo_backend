package com.rapigo.backend_rapigo.service;

import com.rapigo.backend_rapigo.dto.CartResponse;
import com.rapigo.backend_rapigo.model.Cart;
import com.rapigo.backend_rapigo.model.CartItem;
import com.rapigo.backend_rapigo.model.Coupon;
import com.rapigo.backend_rapigo.model.MenuItem;
import com.rapigo.backend_rapigo.model.Order;
import com.rapigo.backend_rapigo.repository.CartRepository;
import com.rapigo.backend_rapigo.repository.CouponRepository;
import com.rapigo.backend_rapigo.repository.MenuItemRepository;
import com.rapigo.backend_rapigo.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartService {

    public static final double DELIVERY_FEE = 5.00;

    private final CartRepository cartRepository;
    private final MenuItemRepository menuItemRepository;
    private final CouponRepository couponRepository;
    private final OrderRepository orderRepository;

    public CartService(CartRepository cartRepository, MenuItemRepository menuItemRepository,
                       CouponRepository couponRepository, OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.menuItemRepository = menuItemRepository;
        this.couponRepository = couponRepository;
        this.orderRepository = orderRepository;
    }

    public Cart createCart() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }

    private double subtotal(Cart c) {
        return c.getItems().stream().mapToDouble(item -> item.getPrice() * item.getQty()).sum();
    }

    private double discount(Cart c) {
        double sub = subtotal(c);
        double disc = 0.0;
        for (String code : c.getAppliedCoupons()) {            // cada cupón en la lista descuenta (acumula si está repetido)
            Coupon coupon = couponRepository.findById(code).orElse(null);
            if (coupon != null) disc += sub * coupon.getPercent() / 100.0;
        }
        return disc;
    }

    public CartResponse getCart(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow();
        double sub = subtotal(cart);
        double disc = discount(cart);
        CartResponse response = new CartResponse();
        response.setId(cart.getId());
        response.setItems(cart.getItems());
        response.setAppliedCoupons(cart.getAppliedCoupons());
        response.setSubtotal(sub);
        response.setDiscount(disc);
        response.setDeliveryFee(DELIVERY_FEE);
        response.setTotal(sub - disc); // BUG RG-204: SIN clamp a 0; el total puede quedar negativo
        return response;
    }

    public CartResponse addItem(Long cartId, Long menuItemId, int qty) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        MenuItem mi = menuItemRepository.findById(menuItemId).orElseThrow();
        cart.getItems().add(new CartItem(menuItemId, mi.getName(), mi.getPrice(), qty));
        cartRepository.save(cart);
        return getCart(cartId);
    }

    public CartResponse applyCoupon(Long cartId, String code) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        // BUG RG-204: NO se verifica si el cupón ya fue aplicado; se agrega siempre, permitiendo acumular el descuento
        cart.getAppliedCoupons().add(code);
        cartRepository.save(cart);
        return getCart(cartId);
    }

    public Order checkout(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        double sub = subtotal(cart);
        double disc = discount(cart);
        Order order = new Order();
        order.setItems(new ArrayList<>(cart.getItems()));
        order.setSubtotal(sub);
        order.setDiscount(disc);
        order.setDeliveryFee(DELIVERY_FEE);
        order.setTotal(sub - disc); // BUG RG-205: olvida sumar deliveryFee (debería ser sub - disc + DELIVERY_FEE)
        order.setStatus("CONFIRMED");
        return orderRepository.save(order);
    }
}
