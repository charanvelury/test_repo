package com.example.AllConceptsDemoWithSpringBoot.model.ApiResponse;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class FileBasedOnIdResponse {
private boolean success;
private String username;
private Date uploadTime;
private String filename;
private String content;

}
