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
public class CustomerNotFoundException extends RuntimeException{

    @JsonProperty("message")
    private String message = "Customer Not Found in Database";

}
