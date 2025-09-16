package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.model.*;
import com.e_commerce.e_commerce.repository.OrderRepository;
import com.e_commerce.e_commerce.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public OrderDetails createOrderFromCart(UserData user) {
        List<Cart> cartItems = cartService.getCartItems(user);
        if (cartItems.isEmpty()) {
            throw new IllegalStateException("Cannot create order from an empty cart.");
        }

        OrderDetails order = new OrderDetails();
        order.setUserData(user);

        List<OrderItem> orderItems = cartItems.stream().map(cartItem -> {
            OrderItem item = new OrderItem();
            item.setProduct(cartItem.getProduct());
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(cartItem.getProduct().getPrice());
            item.setOrderDetails(order);
            return item;
        }).collect(Collectors.toList());

        order.setOrderItems(orderItems);

        double totalAmount = orderItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        order.setTotalAmount(totalAmount);

        return orderRepository.save(order);
    }

    @Override
    public OrderDetails findOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
    }

    @Override
    public void processPaymentAndFinalizeOrder(Integer orderId) {
        OrderDetails order = findOrderById(orderId);
        // If no payment exists, create a successful payment by default
        PaymentData payment = paymentRepository.findByOrderDetails(order).orElse(null);
        if (payment == null) {
            payment = new PaymentData();
            payment.setOrderDetails(order);
            payment.setAmount(order.getTotalAmount());
            payment.setPaymentStatus(PaymentStatus.COMPLETED);
            payment.setPaymentDate(new Date());
            paymentRepository.save(payment);
        }
        if (payment.getPaymentStatus() == null || !payment.getPaymentStatus().name().equalsIgnoreCase("COMPLETED")) {
            throw new IllegalStateException("Payment not successful. Please complete payment before placing the order.");
        }
        for (OrderItem item : order.getOrderItems()) {
            Product product = item.getProduct();
            int newStockQuantity = product.getStockQuantity() - item.getQuantity();
            if (newStockQuantity < 0) {
                throw new IllegalStateException("Insufficient stock for product: " + product.getName());
            }
            product.setStockQuantity(newStockQuantity);
            productService.saveProduct(product);
        }
        order.setOrderStatus(OrderStatus.PENDING);
        cartService.clearCart(order.getUserData());
        orderRepository.save(order);
    }

    @Override
    public List<OrderDetails> findOrdersByUser(UserData user) {
        return orderRepository.findByUserDataOrderByOrderDateDesc(user);
    }

    @Override
    public List<OrderDetails> findAllOrders() {
        return orderRepository.findAll(Sort.by(Sort.Direction.DESC, "orderDate"));
    }

    @Override
    public void updateOrderStatus(Integer orderId, OrderStatus status) {
        OrderDetails order = findOrderById(orderId);
        order.setOrderStatus(status);
        orderRepository.save(order);
    }
}