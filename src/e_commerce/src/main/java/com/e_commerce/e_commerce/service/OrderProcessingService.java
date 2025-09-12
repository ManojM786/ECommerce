package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.model.Order;
import com.e_commerce.e_commerce.model.UserData;

import java.util.List;

public interface OrderProcessingService {
    Order createOrder(int userId, Order order);
    List<Order> getCurrentOrder(Order orderId);

    List<Order> getOrderHistory(int userId);

    Order updateOrder(int orderId, int userId, Order updatedOrder);

    boolean cancelOrder(int userId, int orderId);
}
