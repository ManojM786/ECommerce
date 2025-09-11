package com.e_commerce.e_commerce.repository;

import com.e_commerce.e_commerce.model.PaymentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentManagementDAO extends JpaRepository<PaymentData , Integer> {

}
