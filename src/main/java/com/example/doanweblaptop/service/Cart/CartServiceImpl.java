package com.example.doanweblaptop.service.Cart;

import com.example.doanweblaptop.dto.CartDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
@SessionScope
@Service
public class CartServiceImpl implements CartService {
    Map<Long, CartDTO> cartRepository = new HashMap<>();

    @Override
    public void add(CartDTO item) {
        CartDTO cartDTO = cartRepository.get(item.getId());
        if (cartDTO == null) cartRepository.put(item.getId(), item);
        else cartDTO.setQty(cartDTO.getQty() + 1);
    }
    @Override
    public void addTotal(CartDTO item,int qty) {
        CartDTO cartDTO = cartRepository.get(item.getId());
        if (cartDTO == null) cartRepository.put(item.getId(), item);
        else cartDTO.setQty(cartDTO.getQty() + qty);
    }
    @Override
    public void remove(Long id) {
        cartRepository.remove(id);
    }

    @Override
    public CartDTO update(Long id, int qty) {
        CartDTO cartDTO = cartRepository.get(id);
        cartDTO.setQty(qty);
        return cartDTO;
    }

    @Override
    public void clear() {
        cartRepository.clear();
    }

    @Override
    public Collection<CartDTO> getAll() {
        return cartRepository.values();
    }

    @Override
    public int getCount() {
        return cartRepository.values().size();
    }

    @Override
    public double getAmount() {
        return cartRepository.values().stream().mapToDouble(item->item.getQty()* item.getPrice()).sum();
    }

    @Override
    public String getAmountVND() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return currencyFormat.format(getAmount());
    }
}
