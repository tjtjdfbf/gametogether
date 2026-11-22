package com.ryul.gametogether.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice 
public class GlobalExceptionHandler {
    @ExceptionHandler (InvalidCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCredentials(InvalidCredentialsException e) {
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(Map.of("message", e.getMessage()));
    }

    @ExceptionHandler (IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArguemnt(IllegalArgumentException e) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(Map.of("message", e.getMessage()));
    }
}
