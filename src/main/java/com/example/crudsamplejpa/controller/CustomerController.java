package com.example.crudsamplejpa.controller;

import com.example.crudsamplejpa.domain.Customer;
import com.example.crudsamplejpa.dto.CustomerDto;
import com.example.crudsamplejpa.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping
    public Customer createCustomer(@RequestBody CustomerDto dto){
        return customerService.createCustomer(dto);
    }

    @GetMapping

    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    //Le agrego algo
    @GetMapping("/older")
    public List<Customer> getOlderCustomer(){
       return customerService.getOlderCustomers();
    }

    @PutMapping
    public Customer updateCustoomer(@RequestBody CustomerDto dto){ return customerService.updateCustomer(dto); }

    @DeleteMapping
    public Customer deleteCustomer(@RequestBody CustomerDto dto){
        return customerService.deleteCustomer(dto.getId().toString());
    }
}
