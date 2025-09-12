package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.model.Order;
import com.e_commerce.e_commerce.model.Status;
import com.e_commerce.e_commerce.model.UserData;
import com.e_commerce.e_commerce.repository.OrderProcessingDAO;
import com.e_commerce.e_commerce.repository.UserManagementDAO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OrderProcessingServiceImpl implements OrderProcessingService {

    @Autowired
    OrderProcessingDAO orderProcessing;

    @Autowired
    private UserManagementDAO userRepository;

    @Override
    public Order createOrder(int userId, Order order) {
        UserData user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        order.setUser((UserData) user);
        return orderProcessing.save(order);
    }
    @Override
    public List<Order> getOrderHistory(int userId) {
        return orderProcessing.findByUser_UserId(userId).orElse(Collections.emptyList());
    }

    @Override
    public Order updateOrder(int orderId, int userId, Order updatedOrder) {
        Optional<Order> orderOpt = orderProcessing.findByUser_UserIdAndOrderId(userId, orderId);

        if (orderOpt.isPresent()) {
            Order existingOrder = orderOpt.get();
            existingOrder.setTotalAmount(updatedOrder.getTotalAmount());
            existingOrder.setStatus(updatedOrder.getStatus());
            existingOrder.setOrderDate(updatedOrder.getOrderDate());
            return orderProcessing.save(existingOrder);
        } else {
            throw new RuntimeException("Order not found for user ID: " + userId + " and order ID: " + orderId);
        }
    }
    @Override
    public boolean cancelOrder(int userId, int orderId) {
        Optional<Order> orderOpt = orderProcessing.findByUser_UserIdAndOrderId(userId, orderId);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            order.setStatus(Status.CANCELLED);
            orderProcessing.save(order);
            return true;
        }
        return false;
    }

    @Override
    public List<Order> getCurrentOrder(Order orderId) {
        return List.of();
    }
}
