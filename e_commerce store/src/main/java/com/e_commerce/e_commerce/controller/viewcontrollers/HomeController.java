package com.e_commerce.e_commerce.controller.viewcontrollers;

import com.e_commerce.e_commerce.model.Product;
import com.e_commerce.e_commerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("categorizedProducts", productService.findTopProductsForHomepage());
        return "index";
    }

    @GetMapping("/product/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        product.ifPresent(p -> model.addAttribute("product", p));
        return product.isPresent() ? "product-detail" : "redirect:/";
    }
}