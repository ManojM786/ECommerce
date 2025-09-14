package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategories();
    void saveCategory(Category category);
    Optional<Category> getCategoryById(Long id);
    void deleteCategory(Long id);
}
