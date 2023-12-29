package com.example.doanweblaptop.dto;



import com.example.doanweblaptop.entity.LapTop;

import java.text.NumberFormat;
import java.util.Locale;

public class CartDTO {
    private Long id;
    private  LapTop lapTop;
    private String name;
    private double price;
    private int qty=1;
    public String getFormattedPrice() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return currencyFormat.format(this.price);
    }
    public String getFormattedAmount() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return currencyFormat.format(this.price*this.qty);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public CartDTO() {
    }

    public LapTop getLapTop() {
        return lapTop;
    }

    public void setLapTop(LapTop lapTop) {
        this.lapTop = lapTop;
    }

    public CartDTO(Long id, LapTop lapTop, String name, double price, int qty) {
        this.id = id;
        this.lapTop = lapTop;
        this.name = name;
        this.price = price;
        this.qty = qty;
    }

    public CartDTO(Long id, String name, double price, int qty) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
    }
}
