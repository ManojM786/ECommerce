package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.model.OrderDetails;
import com.e_commerce.e_commerce.model.OrderStatus;
import com.e_commerce.e_commerce.model.UserData;
import com.e_commerce.e_commerce.repository.OrderProcessingDAO;
import com.e_commerce.e_commerce.repository.UserManagementDAO;
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
    public OrderDetails createOrder(int userId, OrderDetails orderDetails) {
        UserData user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        orderDetails.setUserData(user);
        return orderProcessing.save(orderDetails);
    }

    @Override
    public List<OrderDetails> getOrderHistory(int userId) {
        return orderProcessing.findByUserData_UserId(userId).orElse(Collections.emptyList());
    }

    @Override
    public OrderDetails updateOrder(int orderId, int userId, OrderDetails updatedOrderDetails) {
        Optional<OrderDetails> orderOpt = orderProcessing.findByUserData_UserIdAndOrderId(userId, orderId);
        if (orderOpt.isPresent()) {
            OrderDetails existingOrder = orderOpt.get();
            existingOrder.setTotalAmount(updatedOrderDetails.getTotalAmount());
            existingOrder.setOrderStatus(updatedOrderDetails.getOrderStatus());
            existingOrder.setOrderDate(updatedOrderDetails.getOrderDate());
            return orderProcessing.save(existingOrder);
        } else {
            throw new RuntimeException("Order not found for user ID: " + userId + " and order ID: " + orderId);
        }
    }

    @Override
    public boolean cancelOrder(int userId, int orderId) {
        Optional<OrderDetails> orderOpt = orderProcessing.findByUserData_UserIdAndOrderId(userId, orderId);
        if (orderOpt.isPresent()) {
            OrderDetails order = orderOpt.get();
            order.setOrderStatus(OrderStatus.CANCELLED);
            orderProcessing.save(order);
            return true;
        }
        return false;
    }

    @Override
    public List<OrderDetails> getCurrentOrder(OrderDetails orderDetails) {
        return List.of();
    }
}
