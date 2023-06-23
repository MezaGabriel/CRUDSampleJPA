package com.example.crudsamplejpa.data;

import com.example.crudsamplejpa.domain.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends CrudRepository<Customer, UUID> {

    List<Customer> getOlderCustomer();

}
