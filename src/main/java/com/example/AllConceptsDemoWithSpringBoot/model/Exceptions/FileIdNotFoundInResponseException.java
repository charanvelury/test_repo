package com.example.AllConceptsDemoWithSpringBoot.model.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileIdNotFoundInResponseException extends RuntimeException{
    public FileIdNotFoundInResponseException(String message)
    {
        super(message);
    }
}
