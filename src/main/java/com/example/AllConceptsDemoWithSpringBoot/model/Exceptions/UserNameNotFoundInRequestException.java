package com.example.AllConceptsDemoWithSpringBoot.model.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNameNotFoundInRequestException extends RuntimeException {
    public UserNameNotFoundInRequestException(String message) {
        super(message);
    }
}
