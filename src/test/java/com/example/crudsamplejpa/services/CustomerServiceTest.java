package com.example.crudsamplejpa.services;

import com.example.crudsamplejpa.data.CustomerRepository;
import com.example.crudsamplejpa.domain.Customer;
import com.example.crudsamplejpa.dto.CustomerDto;
import com.example.crudsamplejpa.exceptions.CustomerNotFoundException;
import com.example.crudsamplejpa.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    CustomerRepository database;

    @InjectMocks
    CustomerService service;

    @Test
    public void testCreateCustomer(){

        CustomerDto customerDto = new CustomerDto();

        customerDto.setNombre("CHELO");
        customerDto.setAge(32);

        Customer result = service.createCustomer(customerDto);

        assertThat(result)
                .hasFieldOrPropertyWithValue("nombre","CHELO")
                .hasFieldOrPropertyWithValue("age", 32);

        assertThat(result.getId())
                .isNotNull();

        verify(database,times(1)).save(any());

    }

    @Test
    public void testGetCustomers(){
        List<Customer> customers = List.of(
                new Customer("Chelo", 23),
                new Customer("Gabriel", 31),
                new Customer("Agustin", 52)
        );

        when(service.getCustomers()).thenReturn(customers);

        var result = service.getCustomers();

        assertThat(result)
                .isEqualTo(customers);
    }


    @Test
    public void testDeleteCustomersCustomerNotFound(){
        List<Customer> customers = List.of(
                new Customer("Chelo", 23),
                new Customer("Gabriel", 31),
                new Customer("Agustin", 52)
        );

        assertThatThrownBy(() -> service.deleteCustomer(customers.get(1).getId().toString()))
                .isExactlyInstanceOf(CustomerNotFoundException.class);

        verify(database,times(1)).findById(any());
    }

    @Test
    public void testDeleteCustomer(){
        Customer customer = new Customer("Chelo",25);

        //Stubear un metodo
        when(database.findById(customer.getId())).thenAnswer(id -> {
                System.out.println("Llamando a la bd con id " + id);
                return Optional.of(customer);
        });

        assertThatCode(() -> service.deleteCustomer(customer.getId().toString()))
                .doesNotThrowAnyException();

        verify(database).findById(customer.getId());

        verify(database,times(1)).delete(any());

    }

    @Test
    public void testUpdateCustomersNotFound(){

        CustomerDto customerDto = new CustomerDto();

        assertThatThrownBy(() -> service.updateCustomer(customerDto))
                .isExactlyInstanceOf(CustomerNotFoundException.class);

        verify(database,times(1)).findById(any());
    }

    @Test
    public void testUpdateCustomers(){

        Customer customer = new Customer("Chelo",25);

        //Stubear un metodo
        when(database.findById(customer.getId())).thenReturn(Optional.of(customer));

        assertThatCode(() -> service.deleteCustomer(customer.getId().toString()))
                .doesNotThrowAnyException();

        verify(database).findById(customer.getId());

        verify(database,times(1)).delete(any());
    }
}
