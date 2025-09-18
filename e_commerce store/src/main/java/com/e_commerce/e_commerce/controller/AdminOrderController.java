package com.e_commerce.e_commerce.controller;

import com.e_commerce.e_commerce.model.OrderDetails;
import com.e_commerce.e_commerce.model.OrderStatus;
import com.e_commerce.e_commerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String listAllOrders(Model model) {
        List<OrderDetails> orders = orderService.findAllOrders();
        model.addAttribute("orders", orders);
        return "admin-orders-list";
    }

    @GetMapping("/{id}")
    public String viewOrder(@PathVariable("id") Integer orderId, Model model) {
        OrderDetails order = orderService.findOrderById(orderId);
        model.addAttribute("order", order);
        model.addAttribute("allStatuses", OrderStatus.values());
        model.addAttribute("payment", orderService.findPaymentByOrder(order));
        return "admin-order-detail";
    }

    @PostMapping("/update-status")
    public String updateOrderStatus(@RequestParam("orderId") Integer orderId,
                                    @RequestParam("status") OrderStatus status) {
        orderService.updateOrderStatus(orderId, status);
        return "redirect:/admin/orders/" + orderId;
    }
}