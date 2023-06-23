package com.example.crudsamplejpa.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Builder
@Getter
@Setter
public class ExceptionDto {
    private String exceptionType;
    private String message;
    private String requestUrl;

    private String requestMethod;
}
