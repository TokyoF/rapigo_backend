package com.rapigo.backend_rapigo.dto;

import com.rapigo.backend_rapigo.model.CartItem;
import java.util.List;

public class CartResponse {

    private Long id;
    private List<CartItem> items;
    private List<String> appliedCoupons;
    private double subtotal;
    private double discount;
    private double deliveryFee;
    private double total;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public List<CartItem> getItems() { return items; }
    public void setItems(List<CartItem> items) { this.items = items; }

    public List<String> getAppliedCoupons() { return appliedCoupons; }
    public void setAppliedCoupons(List<String> appliedCoupons) { this.appliedCoupons = appliedCoupons; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }

    public double getDeliveryFee() { return deliveryFee; }
    public void setDeliveryFee(double deliveryFee) { this.deliveryFee = deliveryFee; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}
