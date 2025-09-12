package com.e_commerce.e_commerce.repository;

import com.e_commerce.e_commerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderProcessingDAO extends JpaRepository<Order,Integer> {
    Optional<List<Order>> findByUser_UserId(int userId);
    Optional<Order> findByUser_UserIdAndOrderId(int userId, long orderId);
}