package com.rapigo.backend_rapigo.dto;

public class AddItemRequest {

    private Long menuItemId;
    private int qty;

    public Long getMenuItemId() { return menuItemId; }
    public void setMenuItemId(Long menuItemId) { this.menuItemId = menuItemId; }

    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }
}
