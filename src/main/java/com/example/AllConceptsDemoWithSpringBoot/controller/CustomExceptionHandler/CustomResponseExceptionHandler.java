package com.example.AllConceptsDemoWithSpringBoot.controller.CustomExceptionHandler;

import com.example.AllConceptsDemoWithSpringBoot.model.Exceptions.*;
import com.example.AllConceptsDemoWithSpringBoot.model.Exceptions.Response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNameNotFoundInRequestException.class)
    public final ResponseEntity<Object> handleUserNameNotFoundInRequestException(UserNameNotFoundInRequestException ex, WebRequest request) throws Exception {
      ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),ex.getMessage(),"User name not found in request - "+request.getDescription(false));
      return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileNameNotFoundInRequestException.class)
    public final ResponseEntity<Object> handleFileNameNotFoundInRequestException(FileNameNotFoundInRequestException ex, WebRequest request) throws Exception {
        ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),ex.getMessage(),"File name not found in request - "+request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileIdNotFoundInResponseException.class)
    public final ResponseEntity<Object> handleFileIdNotFoundInResponseException(FileIdNotFoundInResponseException ex, WebRequest request) throws Exception {
        ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),ex.getMessage(),"File Id not found in response - "+request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNameNotFoundInResponseException.class)
    public final ResponseEntity<Object> handleUserNameNotFoundInResponseException(UserNameNotFoundInResponseException ex, WebRequest request) throws Exception {
        ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),ex.getMessage(),"User name not found in response - "+request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WrongFileExtensionException.class)
    public final ResponseEntity<Object> handleWrongFileExtensionException(WrongFileExtensionException ex, WebRequest request) throws Exception {
        ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),ex.getMessage(),"Wrong file extension exception because the uploaded file is not a text file - "+request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidFileUuidFormatException.class)
    public final ResponseEntity<Object> handleInvalidFileUuidFormatException(InvalidFileUuidFormatException ex, WebRequest request) throws Exception {
        ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),ex.getMessage()," Input File ID is is not a valid UUID format. Please provide ID in UUID format - "+request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    }
