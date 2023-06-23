package com.example.crudsamplejpa.controller;

import com.example.crudsamplejpa.domain.Customer;
import com.example.crudsamplejpa.domain.Product;
import com.example.crudsamplejpa.dto.CustomerDto;
import com.example.crudsamplejpa.dto.ProductDto;
import com.example.crudsamplejpa.service.CustomerService;
import com.example.crudsamplejpa.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public Product createProduct(@RequestBody ProductDto dto){
        return productService.createProduct(dto);
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/expensive")
    public List<Product> getExpensiveProducts(){
        return productService.getExpensiveProducts();
    }

    @PutMapping
    public Product updateProduct(@RequestBody ProductDto dto){ return productService.updateProduct(dto); }

    @DeleteMapping
    public Product deleteProduct(@RequestBody ProductDto dto){ return productService.deleteProduct(dto.getId().toString()); }
}
