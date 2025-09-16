package com.e_commerce.e_commerce.controller;

import com.e_commerce.e_commerce.model.PaymentData;
import com.e_commerce.e_commerce.model.OrderDetails;
import com.e_commerce.e_commerce.model.PaymentStatus;
import com.e_commerce.e_commerce.repository.PaymentRepository;
import com.e_commerce.e_commerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderRepository orderRepository;

    // Mock payment processing
    @PostMapping("/process")
    public String processPayment(@RequestParam int orderId, @RequestParam double amount, @RequestParam(required = false) String status, Model model) {
        Optional<OrderDetails> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isEmpty()) {
            model.addAttribute("message", "Order not found");
            return "payment-status";
        }
        OrderDetails order = orderOpt.get();
        PaymentData payment = new PaymentData();
        payment.setOrderDetails(order);
        payment.setAmount(amount);
        PaymentStatus paymentStatus = PaymentStatus.COMPLETED;
        if (status != null) {
            try {
                paymentStatus = PaymentStatus.valueOf(status.toUpperCase());
            } catch (Exception ignored) {}
        }
        payment.setPaymentStatus(paymentStatus);
        payment.setPaymentDate(new Date());
        paymentRepository.save(payment);
        model.addAttribute("message", "Payment processed. Payment ID: " + payment.getPaymentId() + ", Status: " + payment.getPaymentStatus());
        return "payment-status";
    }

    @GetMapping("/status")
    public String getPaymentStatus(@RequestParam(required = false) Integer orderId, @RequestParam(required = false) Integer paymentId, Model model) {
        Optional<PaymentData> paymentOpt = Optional.empty();
        if (paymentId != null) {
            paymentOpt = paymentRepository.findById(paymentId);
        } else if (orderId != null) {
            Optional<OrderDetails> orderOpt = orderRepository.findById(orderId);
            if (orderOpt.isPresent()) {
                paymentOpt = paymentRepository.findByOrderDetails(orderOpt.get());
            }
        }
        if (paymentOpt.isEmpty()) {
            model.addAttribute("message", "No payment found for the given criteria");
            return "payment-status";
        }
        PaymentData payment = paymentOpt.get();
        model.addAttribute("message", "Payment Status: SUCCESS. Payment ID: " + payment.getPaymentId());
        return "payment-status";
    }
}
