package com.example.AllConceptsDemoWithSpringBoot.model.Dto.objectconvert;

import com.example.AllConceptsDemoWithSpringBoot.model.ApiResponse.FileBasedOnIdResponse;
import com.example.AllConceptsDemoWithSpringBoot.model.ApiResponse.FileDataResponse;
import com.example.AllConceptsDemoWithSpringBoot.model.Dto.FileData;

import java.util.function.Function;

public class ConvertFileDataToFileBasedOnIdResponse implements Function<FileData, FileBasedOnIdResponse> {
    public FileBasedOnIdResponse apply(FileData fileData) {
        FileBasedOnIdResponse fileDataResponse=new FileBasedOnIdResponse();
        fileDataResponse.setSuccess(true);
        fileDataResponse.setUsername(fileData.getFileUploadedByUser());
        fileDataResponse.setUploadTime(fileData.getFileUploadTime());
        fileDataResponse.setFilename(fileData.getFileName());
        fileDataResponse.setContent(fileData.getContent());

        return fileDataResponse;
    }
}
