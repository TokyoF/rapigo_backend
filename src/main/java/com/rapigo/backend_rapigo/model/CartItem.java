package com.rapigo.backend_rapigo.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class CartItem {

    private Long menuItemId;
    private String name;
    private double price;
    private int qty;

    public CartItem() {}

    public CartItem(Long menuItemId, String name, double price, int qty) {
        this.menuItemId = menuItemId;
        this.name = name;
        this.price = price;
        this.qty = qty;
    }

    public Long getMenuItemId() { return menuItemId; }
    public void setMenuItemId(Long menuItemId) { this.menuItemId = menuItemId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }
}
