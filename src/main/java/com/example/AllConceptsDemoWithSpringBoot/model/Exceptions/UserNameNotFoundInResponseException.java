package com.example.AllConceptsDemoWithSpringBoot.model.Exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@Setter
@Getter

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNameNotFoundInResponseException extends RuntimeException {
    public UserNameNotFoundInResponseException(String message)
    {
        super(message);
    }
}
