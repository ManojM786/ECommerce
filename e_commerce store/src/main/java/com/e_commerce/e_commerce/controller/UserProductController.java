package com.e_commerce.e_commerce.controller;

import com.e_commerce.e_commerce.model.Product;
import com.e_commerce.e_commerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequestMapping("/products")
public class UserProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam("query") String query, Model model) {
        String trimmedQuery = query == null ? "" : query.trim();
        List<Product> products = productService.searchProducts(trimmedQuery);
        model.addAttribute("products", products);
        model.addAttribute("searchQuery", trimmedQuery);
        return "productsList";
    }

}
