package com.example.passman.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;


@ControllerAdvice
public class ValidationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException validException){
        // LinkedHashMap is used to guarantee proper ordering, and to support O(1) additions. Ordering is supported for
        // convenience of the user.
        Map<String, String> errorsMap = new LinkedHashMap<>();
        validException.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errorsMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                }

        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsMap);

    }
}
