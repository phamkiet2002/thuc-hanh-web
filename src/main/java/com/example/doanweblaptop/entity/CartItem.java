package com.example.doanweblaptop.entity;

public class CartItem {
    private LapTop lapTop;
    private int quantity;

    public CartItem() {
    }

    public CartItem(LapTop lapTop, int quantity) {
        this.lapTop = lapTop;
        this.quantity = quantity;
    }

    public LapTop getLapTop() {
        return lapTop;
    }

    public void setLapTop(LapTop lapTop) {
        this.lapTop = lapTop;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
