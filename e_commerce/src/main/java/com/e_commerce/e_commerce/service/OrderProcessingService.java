package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.model.OrderDetails;

import java.util.List;

public interface OrderProcessingService {
    OrderDetails createOrder(int userId, OrderDetails orderDetails);
    List<OrderDetails> getCurrentOrder(OrderDetails orderDetails);

    List<OrderDetails> getOrderHistory(int userId);

    OrderDetails updateOrder(int orderId, int userId, OrderDetails updatedOrderDetails);

    boolean cancelOrder(int userId, int orderId);
}
