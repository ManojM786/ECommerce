package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.model.Product;
import com.e_commerce.e_commerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public String saveImage(MultipartFile imageFile) throws IOException {

        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }


        String originalFilename = imageFile.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uniqueFilename = UUID.randomUUID().toString() + fileExtension;


        Path filePath = uploadPath.resolve(uniqueFilename);


        Files.copy(imageFile.getInputStream(), filePath);

        return "/uploads/" + uniqueFilename;
    }

    @Override
    public Map<String, List<Product>> findTopProductsForHomepage() {
        List<String> categories = Arrays.asList("Electronics", "Fashion", "Home & Kitchen");
        Map<String, List<Product>> productsByCategory = new LinkedHashMap<>();
        for (String categoryName : categories) {
            // This requires the new method in ProductRepository
            List<Product> products = productRepository.findTop5ByCategory_NameOrderByProductIdDesc(categoryName);
            if (!products.isEmpty()) {
                productsByCategory.put(categoryName, products);
            }
        }
        return productsByCategory;
    }
}