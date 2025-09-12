package com.e_commerce.e_commerce.controller.restcontroller;

import com.e_commerce.e_commerce.model.ApiReturnData;
import com.e_commerce.e_commerce.model.ApiStatus;
import com.e_commerce.e_commerce.model.Product;
import com.e_commerce.e_commerce.service.ProductManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductManagementRest {
    @Autowired
    ProductManagementServiceImpl productService;
    @GetMapping("/products")
    public ResponseEntity<ApiReturnData> getProducts(){
        List<Product> products =  productService.getAllProducts();
        ApiReturnData apiReturnData = new ApiReturnData();
        if(products.isEmpty()){
            apiReturnData.setApiResponseStatus(ApiStatus.NOT_FOUND);
            apiReturnData.setApiResponseMessage("No Products found");
            return ResponseEntity.badRequest().body(apiReturnData);
        }
        apiReturnData.setApiResponseData(products);
        apiReturnData.setApiResponseStatus(ApiStatus.SUCCESS);
        apiReturnData.setApiResponseMessage("Products Fetched Successfully");
        return ResponseEntity.ok().body(apiReturnData);
    }
}