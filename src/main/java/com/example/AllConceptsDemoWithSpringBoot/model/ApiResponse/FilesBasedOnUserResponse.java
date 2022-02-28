package com.example.AllConceptsDemoWithSpringBoot.model.ApiResponse;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class FilesBasedOnUserResponse {
    private String status;
    private String username;
    private List<FileDataResponse> files;

}
