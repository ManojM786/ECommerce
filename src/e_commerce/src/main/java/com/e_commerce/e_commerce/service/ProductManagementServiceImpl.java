package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.model.Product;
import com.e_commerce.e_commerce.repository.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductManagementServiceImpl implements ProductManagementService{

    @Autowired
    ProductDAO productRepo;

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepo.findAll();
        return products;
    }

    @Override
    public boolean saveProduct(Product product) {
//        product.findById();
//        productRepo.save(product);
        return false;
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteProduct(Long id) {
        return false;
    }
}
