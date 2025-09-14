package com.e_commerce.e_commerce.controller.viewcontrollers;

import com.e_commerce.e_commerce.model.Cart;
import com.e_commerce.e_commerce.model.UserData;
import com.e_commerce.e_commerce.repository.UserManagementDAO;
import com.e_commerce.e_commerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserManagementDAO userManagementDAO;

    private UserData getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        return userManagementDAO.findByEmail(currentUserName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + currentUserName));
    }

    @GetMapping
    public String viewCart(Model model) {
        UserData user = getCurrentUser();
        List<Cart> cartItems = cartService.getCartItems(user);

        double subtotal = cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        double shipping = subtotal > 0 ? 50.0 : 0.0;
        double total = subtotal + shipping;

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("shipping", shipping);
        model.addAttribute("total", total);

        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam("productId") Long productId, @RequestParam(value = "quantity", defaultValue = "1") int quantity) {
        cartService.addToCart(getCurrentUser(), productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam("cartId") Integer cartId) {
        cartService.removeItemFromCart(cartId);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateCartQuantity(@RequestParam("cartId") Integer cartId, @RequestParam("quantity") int quantity) {
        cartService.updateItemQuantity(cartId, quantity);
        return "redirect:/cart";
    }
}