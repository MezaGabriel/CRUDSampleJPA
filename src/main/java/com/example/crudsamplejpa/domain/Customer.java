package com.example.crudsamplejpa.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NamedQuery;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedNativeQuery(
        name = "Customer.getOlderCustomer",
        query = "SELECT * FROM customer WHERE age > 18",
        resultClass = Customer.class
)
public class
Customer {
    private @Id UUID id;
    private String nombre;
    private int age;

    public Customer(String nombre, int age){
        this(UUID.randomUUID(),nombre,age);
    }

}
