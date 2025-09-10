package com.e_commerce.e_commerce.service;
import com.e_commerce.e_commerce.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductManagementService {
    List<Product> getAllProducts();
    boolean saveProduct(Product product);
    Optional<Product> getProductById(Long id);
    boolean deleteProduct(Long id);
}
