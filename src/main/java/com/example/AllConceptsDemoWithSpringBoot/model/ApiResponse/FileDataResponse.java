package com.example.AllConceptsDemoWithSpringBoot.model.ApiResponse;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
public class FileDataResponse {
private String filename;
private Date uploadTime;
private UUID id;

}
