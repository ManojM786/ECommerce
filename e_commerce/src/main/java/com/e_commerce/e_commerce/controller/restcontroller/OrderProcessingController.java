package com.e_commerce.e_commerce.controller.restcontroller;

import com.e_commerce.e_commerce.model.OrderDetails;
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
    public OrderDetails createOrder(@RequestBody OrderDetails orderDetails, @RequestParam int userId) {
        return orderService.createOrder(userId, orderDetails);
    }

    @GetMapping("/history/{userId}")
    public List<OrderDetails> getOrderHistory(@PathVariable int userId) {
        return orderService.getOrderHistory(userId);
    }

    @PutMapping("/update")
    public OrderDetails updateOrder(@RequestParam int userId, @RequestParam int orderId, @RequestBody OrderDetails updatedOrderDetails) {
        return orderService.updateOrder(orderId, userId, updatedOrderDetails);
    }

    @DeleteMapping("/cancel")
    public boolean cancelOrder(@RequestParam int userId, @RequestParam int orderId) {
        return orderService.cancelOrder(userId, orderId);
    }
}