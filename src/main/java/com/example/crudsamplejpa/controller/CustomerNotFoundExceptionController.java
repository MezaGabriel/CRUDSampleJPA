package com.example.crudsamplejpa.controller;

import com.example.crudsamplejpa.dto.ExceptionDto;
import com.example.crudsamplejpa.exceptions.CustomerNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomerNotFoundExceptionController {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ExceptionDto> methodArgumentNotValidException(HttpServletRequest request, CustomerNotFoundException e) throws IOException {
        var dto = ExceptionDto.builder()
                .exceptionType(e.getClass().getSimpleName())
                .message(e.getMessage())
                .requestUrl(request.getRequestURI())
                .requestMethod(request.getMethod())
                .build();
        return new ResponseEntity<>(dto,HttpStatus.NOT_FOUND);
    }
}
