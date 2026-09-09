package com.example.controllers;

import com.example.dto.ResponseErrorMessage;
import com.example.exceptions.SubscriptionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SubscriptionErrorsHandler {

    @ExceptionHandler(SubscriptionNotFoundException.class)
    public ResponseEntity<ResponseErrorMessage> handleSubscriptionNotFoundException(SubscriptionNotFoundException e) {
        return new ResponseEntity<>(new ResponseErrorMessage(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
