package com.e_commerce.e_commerce.repository;

import com.e_commerce.e_commerce.model.PaymentData;
import com.e_commerce.e_commerce.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentData, Integer> {
    Optional<PaymentData> findByOrderDetails(OrderDetails orderDetails);
}

