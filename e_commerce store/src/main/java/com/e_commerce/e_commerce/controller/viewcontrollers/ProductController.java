package com.e_commerce.e_commerce.controller.viewcontrollers;

import com.e_commerce.e_commerce.model.Category;
import com.e_commerce.e_commerce.model.Product;
import com.e_commerce.e_commerce.service.CategoryService;
import com.e_commerce.e_commerce.service.ProductService;
import com.e_commerce.e_commerce.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product";
    }
    @GetMapping("/add")
    public String showAddForm(Model model){
        model.addAttribute("product", new Product());
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "product-form";
    }
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product, @RequestParam("imageFile") MultipartFile imageFile) {
        try {
            if (!imageFile.isEmpty()) {
                String imagePath = ((ProductServiceImpl) productService).saveImage(imageFile);
                product.setImageUrl(imagePath);
            }
            productService.saveProduct(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            List<Category> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            return "product-form";
        } else {
            return "redirect:/admin/products";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }
}