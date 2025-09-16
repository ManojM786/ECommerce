package com.e_commerce.e_commerce.controller.viewcontrollers;

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

    @Autowired
    private UserManagementDAO userManagementDAO;

    private UserData getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        return userManagementDAO.findByEmail(currentUserName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + currentUserName));
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        try {
            OrderDetails order = orderService.createOrderFromCart(getCurrentUser());
            double shipping = order.getTotalAmount() > 0 ? 50.0 : 0.0;
            model.addAttribute("order", order);
            model.addAttribute("shipping", shipping);
            model.addAttribute("total", order.getTotalAmount() + shipping);
            return "checkout";
        } catch (IllegalStateException e) {
            return "redirect:/cart?error=empty";
        }
    }

    @PostMapping("/order/place")
    public String placeOrder(@RequestParam("orderId") Integer orderId) {
        orderService.processPaymentAndFinalizeOrder(orderId);
        return "redirect:/order/confirmation?orderId=" + orderId;
    }

    @GetMapping("/order/confirmation")
    public String orderConfirmation(@RequestParam("orderId") Integer orderId, Model model) {
        OrderDetails order = orderService.findOrderById(orderId);
        model.addAttribute("order", order);
        return "order-confirmation";
    }

    @GetMapping("/orders")
    public String myOrders(Model model) {
        List<OrderDetails> orders = orderService.findOrdersByUser(getCurrentUser());
        model.addAttribute("orders", orders);
        return "my-orders";
    }

    @GetMapping("/orders/{id}")
    public String orderDetails(@PathVariable("id") Integer orderId, Model model) {
        OrderDetails order = orderService.findOrderById(orderId);
        UserData currentUser = getCurrentUser();
        if (!order.getUserData().getEmail().equals(currentUser.getEmail())) {
            return "redirect:/orders?error=access_denied";
        }

        model.addAttribute("order", order);
        return "order-detail-view";
    }
}