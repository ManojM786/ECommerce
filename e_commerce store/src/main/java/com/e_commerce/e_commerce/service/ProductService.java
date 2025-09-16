package com.e_commerce.e_commerce.service;
import com.e_commerce.e_commerce.model.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    void saveProduct(Product product);
    Optional<Product> getProductById(Long id);
    void deleteProduct(Long id);

    Map<String, List<Product>> findTopProductsForHomepage();
    List<Product> searchProducts(String query);
}