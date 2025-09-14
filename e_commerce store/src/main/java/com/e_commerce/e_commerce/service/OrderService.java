package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.model.OrderDetails;
import com.e_commerce.e_commerce.model.OrderStatus;
import com.e_commerce.e_commerce.model.UserData;

import java.util.List;

public interface OrderService {
    OrderDetails createOrderFromCart(UserData user);
    OrderDetails findOrderById(Integer orderId);
    void processPaymentAndFinalizeOrder(Integer orderId);
    List<OrderDetails> findOrdersByUser(UserData user);
    List<OrderDetails> findAllOrders();
    void updateOrderStatus(Integer orderId, OrderStatus status);
}