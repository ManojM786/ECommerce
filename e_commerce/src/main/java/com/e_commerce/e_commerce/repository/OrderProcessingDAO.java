package com.e_commerce.e_commerce.repository;

import com.e_commerce.e_commerce.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderProcessingDAO extends JpaRepository<OrderDetails,Integer> {
    Optional<List<OrderDetails>> findByUserData_UserId(int userId);
    Optional<OrderDetails> findByUserData_UserIdAndOrderId(int userId, int orderId);
}