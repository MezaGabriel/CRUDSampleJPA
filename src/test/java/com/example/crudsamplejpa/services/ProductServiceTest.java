package com.example.crudsamplejpa.services;

import com.example.crudsamplejpa.data.ProductRepository;
import com.example.crudsamplejpa.domain.Customer;
import com.example.crudsamplejpa.domain.Product;
import com.example.crudsamplejpa.dto.ProductDto;
import com.example.crudsamplejpa.exceptions.ProductNotFoundException;
import com.example.crudsamplejpa.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    ProductRepository database;

    @InjectMocks
    ProductService service;

    @Test
    public void testCreateProduct(){

        ProductDto productDto = new ProductDto();

        productDto.setNombre("Gaseosa");
        productDto.setPrice(32.39);

        Product result = service.createProduct(productDto);

        assertThat(result)
                .hasFieldOrPropertyWithValue("nombre","Gaseosa")
                .hasFieldOrPropertyWithValue("price", 32.39);

        assertThat(result.getId())
                .isNotNull();

        verify(database,times(1)).save(any());

    }

    @Test
    public void testGetProducts(){
        List<Product> products = List.of(
                new Product("Agua", 230.2),
                new Product("Gaseosa", 310.5),
                new Product("Cerveza", 520.1)
        );

        when(service.getProducts()).thenReturn(products);

        var result = service.getProducts();

        assertThat(result)
                .isEqualTo(products);
    }

    @Test
    public void testDeleteProductsNotFound(){
        List<Product> products = List.of(
                new Product("Agua", 233.2),
                new Product("Pan", 310.5),
                new Product("Gaseosa", 521.8)
        );

        assertThatThrownBy(() -> service.deleteProduct(products.get(1).getId().toString()))
                .isExactlyInstanceOf(ProductNotFoundException.class);

        verify(database,times(1)).findById(any());
    }

    @Test
    public void testDeleteProduct(){
        Product product = new Product("Gaseosa",25.43);

        //Stubear un metodo
        when(database.findById(product.getId())).thenAnswer(id -> {
            System.out.println("Llamando a la bd con id " + id);
            return Optional.of(product);
        });

        assertThatCode(() -> service.deleteProduct(product.getId().toString()))
                .doesNotThrowAnyException();

        verify(database).findById(product.getId());

        verify(database,times(1)).delete(any());

    }

    @Test
    public void testUpdateProductsNotFound(){

        ProductDto productDto = new ProductDto();

        assertThatThrownBy(() -> service.updateProduct(productDto))
                .isExactlyInstanceOf(ProductNotFoundException.class);

        verify(database,times(1)).findById(any());
    }

    @Test
    public void testUpdateProducts(){

        Product products = new Product("Gaseosa",25.21);

        //Stubear un metodo
        when(database.findById(products.getId())).thenReturn(Optional.of(products));

        assertThatCode(() -> service.deleteProduct(products.getId().toString()))
                .doesNotThrowAnyException();

        verify(database).findById(products.getId());

        verify(database,times(1)).delete(any());
    }
}
