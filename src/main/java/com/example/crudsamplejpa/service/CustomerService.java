package com.example.crudsamplejpa.service;

import com.example.crudsamplejpa.data.CustomerRepository;
import com.example.crudsamplejpa.domain.Customer;
import com.example.crudsamplejpa.dto.CustomerDto;
import com.example.crudsamplejpa.exceptions.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class
CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(CustomerDto dto){
        var customer = new Customer(dto.getNombre(),dto.getAge());

        customerRepository.save(customer);

        return customer;
    }

    public List<Customer> getCustomers(){

        var resultados = customerRepository.findAll();

        return StreamSupport.stream(resultados.spliterator(),false)
                .collect(Collectors.toList());
    }

    public List<Customer> getOlderCustomers(){
        return customerRepository.getOlderCustomer();
    }

    public Customer deleteCustomer(String id){

        Customer customer = customerRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new CustomerNotFoundException());

        customerRepository.delete(customer);

        return customer;
    }

    public Customer updateCustomer(CustomerDto dto){

        Customer customer = customerRepository.findById(dto.getId())
                .orElseThrow(() -> new CustomerNotFoundException());

        customer.setNombre(dto.getNombre());
        customer.setAge(dto.getAge());
        customerRepository.save(customer);

        return customer;
    }
}
