package com.e_commerce.e_commerce.controller.restcontroller;

import com.e_commerce.e_commerce.model.Order;
import com.e_commerce.e_commerce.service.OrderProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderProcessingController {

    @Autowired
    private OrderProcessingService orderService;

    @PostMapping("/create")
    public Order createOrder(@RequestBody Order order, @RequestParam int userId) {
        return orderService.createOrder(userId, order);
    }

    @GetMapping("/history/{userId}")
    public List<Order> getOrderHistory(@PathVariable int userId) {
        return orderService.getOrderHistory(userId);
    }

    @PutMapping("/update")
    public Order updateOrder(@RequestParam int userId, @RequestParam int orderId, @RequestBody Order updatedOrder) {
        return orderService.updateOrder(orderId, userId, updatedOrder);
    }

    @DeleteMapping("/cancel")
    public boolean cancelOrder(@RequestParam int userId, @RequestParam int orderId) {
        return orderService.cancelOrder(userId, orderId);
    }
}