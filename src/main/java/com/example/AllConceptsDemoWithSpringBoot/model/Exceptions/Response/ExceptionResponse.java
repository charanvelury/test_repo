package com.example.AllConceptsDemoWithSpringBoot.model.Exceptions.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

    private boolean success=false;
    private String errorMsg;

}
