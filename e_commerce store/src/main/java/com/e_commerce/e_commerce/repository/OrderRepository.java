package com.e_commerce.e_commerce.repository;

import com.e_commerce.e_commerce.model.OrderDetails;
import com.e_commerce.e_commerce.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderDetails, Integer> {
    List<OrderDetails> findByUserDataOrderByOrderDateDesc(UserData userData);
}