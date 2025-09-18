package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.model.Cart;
import com.e_commerce.e_commerce.model.Product;
import com.e_commerce.e_commerce.model.UserData;
import com.e_commerce.e_commerce.repository.CartRepository;
import com.e_commerce.e_commerce.repository.ProductRepository;
import com.e_commerce.e_commerce.repository.UserManagementDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Autowired
    private UserManagementDAO userManagementDAO;

    private UserData getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        return userManagementDAO.findByEmail(currentUserName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + currentUserName));
    }

    @Override
    public String addToCart(Long productId, int quantity) {

        UserData user = getCurrentUser();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        Optional<Cart> cartItemOptional = cartRepository.findByUserAndProduct(user, product);
        int currentCartQuantity = cartItemOptional.map(Cart::getQuantity).orElse(0);
        int availableStock = product.getStockQuantity();

        if (currentCartQuantity + quantity > availableStock) {
            return "Requested quantity exceeds available stock for product: " + product.getName();
        }

        if (cartItemOptional.isPresent()) {
            Cart cartItem = cartItemOptional.get();
            cartItem.setQuantity(currentCartQuantity + quantity);
        } else {
            Cart newCartItem = new Cart(null, user, product, quantity);
            cartRepository.save(newCartItem);
        }
        return "Product added to cart successfully.";
    }

    @Override
    public List<Cart> getCartItems() {
        UserData user = getCurrentUser();
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
    public String updateItemQuantity(Integer cartId, int quantity) {
        Cart cartItem = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart item not found with id: " + cartId));
        Product product = cartItem.getProduct();
        int availableStock = product.getStockQuantity();

        if (quantity > availableStock) {
            return "Requested quantity exceeds available stock for product: " + product.getName();
        }

        if (quantity <= 0) {
            cartRepository.delete(cartItem);
            return "Product removed from cart.";
        } else {
            cartItem.setQuantity(quantity);
            cartRepository.save(cartItem);
            return "Cart item updated successfully.";
        }
    }
}