package com.e_commerce.e_commerce.controller;

import com.e_commerce.e_commerce.model.Cart;
import com.e_commerce.e_commerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;



    @GetMapping
    public String viewCart(Model model) {
        List<Cart> cartItems = cartService.getCartItems();

        double subtotal = cartItems.stream()
                .mapToDouble(item -> item.getQuantity()*item.getProduct().getPrice())
                .sum();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("subtotal", subtotal);

        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam("productId") Long productId,
                            RedirectAttributes redirectAttributes) {
        String message = cartService.addToCart(productId, 1);
        redirectAttributes.addFlashAttribute("cartMessage", message);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam("cartId") Integer cartId) {
        cartService.removeItemFromCart(cartId);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateCartQuantity(@RequestParam("cartId") Integer cartId,
                                     @RequestParam("quantity") int quantity,
                                     RedirectAttributes redirectAttributes) {
        String message = cartService.updateItemQuantity(cartId, quantity);
        redirectAttributes.addFlashAttribute("cartMessage", message);
        return "redirect:/cart";
    }
}