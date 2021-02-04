package com.serviceonepavilion.service;

import com.serviceonepavilion.dto.CartDto;
import com.serviceonepavilion.entities.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart getShoppingCart(Integer customerId);
    void addToShoppingCart(CartDto cartDto);
}
