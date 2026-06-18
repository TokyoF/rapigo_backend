package com.rapigo.backend_rapigo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "cart_items")
    private List<CartItem> items = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "cart_coupons")
    private List<String> appliedCoupons = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public List<CartItem> getItems() { return items; }
    public void setItems(List<CartItem> items) { this.items = items; }

    public List<String> getAppliedCoupons() { return appliedCoupons; }
    public void setAppliedCoupons(List<String> appliedCoupons) { this.appliedCoupons = appliedCoupons; }
}
