package com.example.AllConceptsDemoWithSpringBoot.model.ApiResponse;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
public class SuccessfulFileUploadResponse {
        private String status;
        private UUID id;
   }
