package com.example.crudsamplejpa.data;

import com.example.crudsamplejpa.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends CrudRepository<Product, UUID> {
    List<Product> getExpensiveProducts();
}
