package com.e_commerce.e_commerce.controller;

import com.e_commerce.e_commerce.model.OrderDetails;
import com.e_commerce.e_commerce.model.UserData;
import com.e_commerce.e_commerce.repository.UserManagementDAO;
import com.e_commerce.e_commerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/checkout")
    public String checkout(Model model) {
        try {
            OrderDetails order = orderService.createOrderFromCart();
            double shipping = order.getTotalAmount() < 50 ? 10.0 : 0.0;
            model.addAttribute("order", order);
            model.addAttribute("shipping", shipping);
            model.addAttribute("total", order.getTotalAmount() + shipping);
            return "checkout";
        } catch (IllegalStateException e) {
            return "redirect:/cart?error=empty";
        }
    }

    @PostMapping("/order/place")
    public String placeOrder(@RequestParam("orderId") Integer orderId, Model model) {
        try {
            orderService.processPaymentAndFinalizeOrder(orderId);
            return "redirect:/order/confirmation?orderId=" + orderId;
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            OrderDetails order = orderService.findOrderById(orderId);
            model.addAttribute("order", order);
            return "order-confirmation";
        }
    }

    @GetMapping("/order/confirmation")
    public String orderConfirmation(@RequestParam("orderId") Integer orderId, Model model) {
        OrderDetails order = orderService.findOrderById(orderId);
        model.addAttribute("order", order);
        return "order-confirmation";
    }

    @GetMapping("/orders")
    public String myOrders(Model model) {
        List<OrderDetails> orders = orderService.findOrdersByUser();
        model.addAttribute("orders", orders);
        return "my-orders";
    }

    @GetMapping("/orders/{id}")
    public String orderDetails(@PathVariable("id") Integer orderId, Model model) {
        OrderDetails order = orderService.findOrderById(orderId);
        model.addAttribute("order", order);
        return "order-detail-view";
    }
}