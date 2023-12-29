package com.example.doanweblaptop.service.Cart;

import com.example.doanweblaptop.dto.CartDTO;

import java.util.Collection;

public interface CartService {
    void addTotal(CartDTO item,int qty);
    void add(CartDTO item);

    void remove(Long id);

    CartDTO update(Long id, int qty);

    void clear();

    Collection<CartDTO> getAll();

    int getCount();

    double getAmount();
    String getAmountVND();
}
