package com.example.weather.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WeatherControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception ex) {
        ApiResponse errorResponse = new ApiResponse("Error occurred while processing the request", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
