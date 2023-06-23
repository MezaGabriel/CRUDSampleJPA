package com.example.crudsamplejpa.service;

import com.example.crudsamplejpa.controller.CustomerNotFoundExceptionController;
import com.example.crudsamplejpa.data.ProductRepository;
import com.example.crudsamplejpa.domain.Product;
import com.example.crudsamplejpa.dto.ProductDto;
import com.example.crudsamplejpa.exceptions.CustomerNotFoundException;
import com.example.crudsamplejpa.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductDto dto){
        var product = new Product(dto.getNombre(),dto.getPrice());

        productRepository.save(product);

        return product;
    }

    public List<Product> getProducts(){

        var resultados = productRepository.findAll();

        return StreamSupport.stream(resultados.spliterator(),false)
                .collect(Collectors.toList());
    }

    public List<Product> getExpensiveProducts(){
        return productRepository.getExpensiveProducts();
    }

    public Product deleteProduct(String id){

        Product product = productRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ProductNotFoundException());

        productRepository.delete(product);

        return product;
    }

    public Product updateProduct(ProductDto dto){

        Product product = productRepository.findById(dto.getId())
                .orElseThrow(() -> new ProductNotFoundException());

        product.setNombre(dto.getNombre());
        product.setPrice(dto.getPrice());
        productRepository.save(product);

        return product;
    }
}
