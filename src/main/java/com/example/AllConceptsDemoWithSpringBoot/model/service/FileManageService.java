package com.example.AllConceptsDemoWithSpringBoot.model.service;

import com.example.AllConceptsDemoWithSpringBoot.model.ApiResponse.FileBasedOnIdResponse;
import com.example.AllConceptsDemoWithSpringBoot.model.ApiResponse.FileDataResponse;
import com.example.AllConceptsDemoWithSpringBoot.model.ApiResponse.SuccessfulFileUploadResponse;
import com.example.AllConceptsDemoWithSpringBoot.model.Dto.FileData;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FileManageService {
    public SuccessfulFileUploadResponse uploadFile(MultipartFile file, String userName);
    //public List<FileData> getAllFiles();
    public List<FileDataResponse> getFilesBasedOnUploader(String username);
    public Optional<FileBasedOnIdResponse> getFileBasedOnFileId(UUID fileId);

}
