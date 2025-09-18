package com.e_commerce.e_commerce.repository;

import com.e_commerce.e_commerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findTop5ByCategory_NameOrderByProductIdDesc(String categoryName);
    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrBrandContainingIgnoreCase(String name, String description, String brand);
    List<Product> findByNameContainingIgnoreCaseOrBrandContainingIgnoreCaseOrCategory_NameContainingIgnoreCase(String name, String brand, String categoryName);
}
