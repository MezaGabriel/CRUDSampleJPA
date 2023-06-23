package com.example.crudsamplejpa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedNativeQuery(
        name = "Product.getExpensiveProducts",
        query = "SELECT * FROM product WHERE age > 18",
        resultClass = Customer.class
)
public class Product {
    private @Id UUID id;
    private String nombre;
    private double price;

    public Product(String nombre, double price){
        this(UUID.randomUUID(),nombre,price);
    }
}
