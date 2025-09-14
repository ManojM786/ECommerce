package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.model.Cart;
import com.e_commerce.e_commerce.model.Product;
import com.e_commerce.e_commerce.model.UserData;
import com.e_commerce.e_commerce.repository.CartRepository;
import com.e_commerce.e_commerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addToCart(UserData user, Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        Optional<Cart> cartItemOptional = cartRepository.findByUserAndProduct(user, product);

        if (cartItemOptional.isPresent()) {
            Cart cartItem = cartItemOptional.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            Cart newCartItem = new Cart(null, user, product, quantity);
            cartRepository.save(newCartItem);
        }
    }

    @Override
    public List<Cart> getCartItems(UserData user) {
        return cartRepository.findByUser(user);
    }

    @Override
    public void removeItemFromCart(Integer cartId) {
        cartRepository.deleteById(cartId);
    }

    @Override
    public void clearCart(UserData user) {
        cartRepository.deleteByUser(user);
    }

    @Override
    public void updateItemQuantity(Integer cartId, int quantity) {
        Cart cartItem = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart item not found with id: " + cartId));

        if (quantity <= 0) {
            cartRepository.delete(cartItem);
        } else {
            cartItem.setQuantity(quantity);
            cartRepository.save(cartItem);
        }
    }
}