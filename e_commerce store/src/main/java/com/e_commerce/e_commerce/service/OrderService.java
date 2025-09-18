package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.model.OrderDetails;
import com.e_commerce.e_commerce.model.OrderStatus;
import com.e_commerce.e_commerce.model.PaymentData;
import com.e_commerce.e_commerce.model.UserData;

import java.util.List;

public interface OrderService {
    OrderDetails createOrderFromCart();
    OrderDetails findOrderById(Integer orderId);
    void processPaymentAndFinalizeOrder(Integer orderId);
    List<OrderDetails> findOrdersByUser();
    List<OrderDetails> findAllOrders();
    void updateOrderStatus(Integer orderId, OrderStatus status);
    PaymentData findPaymentByOrder(OrderDetails order);
}