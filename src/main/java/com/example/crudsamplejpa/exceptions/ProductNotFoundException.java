package com.example.crudsamplejpa.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductNotFoundException extends RuntimeException{

    @JsonProperty("message")
    private String message = "Product Not Found in Database";

}
