package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.model.Cart;
import com.e_commerce.e_commerce.model.UserData;

import java.util.List;

public interface CartService {
    String addToCart(UserData user, Long productId, int quantity);
    List<Cart> getCartItems(UserData user);
    void removeItemFromCart(Integer cartId);
    void clearCart(UserData user);
    String updateItemQuantity(Integer cartId, int quantity);
}