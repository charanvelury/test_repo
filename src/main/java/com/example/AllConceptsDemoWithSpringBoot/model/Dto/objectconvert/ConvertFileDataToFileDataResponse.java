package com.example.AllConceptsDemoWithSpringBoot.model.Dto.objectconvert;

import com.example.AllConceptsDemoWithSpringBoot.model.ApiResponse.FileDataResponse;
import com.example.AllConceptsDemoWithSpringBoot.model.Dto.FileData;
import java.util.function.Function;

public class ConvertFileDataToFileDataResponse  implements Function<FileData, FileDataResponse> {

    public FileDataResponse apply(FileData fileData) {
        FileDataResponse fileDataResponse=new FileDataResponse();
        fileDataResponse.setFilename(fileData.getFileName());
        fileDataResponse.setUploadTime(fileData.getFileUploadTime());
        fileDataResponse.setId(fileData.getId());
        return fileDataResponse;
    }


}
