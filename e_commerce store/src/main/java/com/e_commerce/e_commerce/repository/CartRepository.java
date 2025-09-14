package com.e_commerce.e_commerce.repository;

import com.e_commerce.e_commerce.model.Cart;
import com.e_commerce.e_commerce.model.Product;
import com.e_commerce.e_commerce.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByUser(UserData user);
    Optional<Cart> findByUserAndProduct(UserData user, Product product);
    void deleteByUser(UserData user);
}