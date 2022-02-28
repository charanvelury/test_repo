package com.example.AllConceptsDemoWithSpringBoot.model.service;

import com.example.AllConceptsDemoWithSpringBoot.model.ApiResponse.FileBasedOnIdResponse;
import com.example.AllConceptsDemoWithSpringBoot.model.ApiResponse.FileDataResponse;
import com.example.AllConceptsDemoWithSpringBoot.model.ApiResponse.FilesBasedOnUserResponse;
import com.example.AllConceptsDemoWithSpringBoot.model.ApiResponse.SuccessfulFileUploadResponse;
import com.example.AllConceptsDemoWithSpringBoot.model.Dto.FileData;
import com.example.AllConceptsDemoWithSpringBoot.model.Dto.objectconvert.ConvertFileDataToFileBasedOnIdResponse;
import com.example.AllConceptsDemoWithSpringBoot.model.Dto.objectconvert.ConvertFileDataToFileDataResponse;
import com.example.AllConceptsDemoWithSpringBoot.repository.FileRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class FileManageServiceDefinitions implements FileManageService {

    private static final Logger LOG = LoggerFactory.getLogger(FileManageServiceDefinitions.class);
    @Value("${project.file.upload.directory.location}")
    private String uploadDirectoryLocation;
    //private String uploadDirectoryLocation="C:\\Users\\saicharan.velury\\Documents\\Process_learning_docs\\Training\\Upload";

    @Autowired
    private FileRepository fileRepo;


    @Override
    public SuccessfulFileUploadResponse uploadFile(MultipartFile file, String userName) {
        //Normalize the path by suppressing sequences like "path/.." and inner simple dots.
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        LOG.info("ORIGINAL FILE NAME::::::"+fileName);
        SuccessfulFileUploadResponse successResponseData = new SuccessfulFileUploadResponse();
        try {
            // we can add additional file validation to discard invalid files
            Path uploadDir = getUploadDirLocation().resolve(fileName);

            //copy the file to the upload directory,it will replace any file with same name.
            Files.copy(file.getInputStream(), uploadDir, StandardCopyOption.REPLACE_EXISTING);

            UUID fileId = UUID.randomUUID();
            FileData filedata=new FileData();

            filedata.setFileName(fileName);
            filedata.setId(fileId);
            filedata.setFileUploadTime(new Date());
            filedata.setFileUploadedByUser(userName);
            filedata.setContent(getFileContent(fileName));

           FileData savedFileData= fileRepo.save(filedata);

            successResponseData.setStatus("success");
            successResponseData.setId(savedFileData.getId());


        } catch (Exception e) {
            LOG.error("unable to cpy file to the target location {}", e);
            //throw new CustomException("Unable to store file " + fileName);
        }
        return successResponseData;
    }

  /*  @Override
    public List<FileData> getAllFiles() {
        return null;
    }*/

    @Override
    public List<FileDataResponse> getFilesBasedOnUploader(String username) {

        List<FileData> usernameBasedFileList=fileRepo.findByFileUploadedByUser(username);
        Function<FileData, FileDataResponse> convertFileDataToFileDataResponse = new ConvertFileDataToFileDataResponse();
        List<FileDataResponse> fileDataResponseList= usernameBasedFileList.stream().map(convertFileDataToFileDataResponse::apply).collect(Collectors.toList());
        return fileDataResponseList;
    }

    @Override
    public Optional<FileBasedOnIdResponse> getFileBasedOnFileId(UUID fileId) {
        Optional<FileData> fileData=fileRepo.findById(fileId);
        Function<FileData, FileBasedOnIdResponse> convertFileDataToFileBasedOnIdResponse = new ConvertFileDataToFileBasedOnIdResponse();
        FileBasedOnIdResponse fileBasedOnIdDataResponse=convertFileDataToFileBasedOnIdResponse.apply(fileData.get());
        return  Optional.of(fileBasedOnIdDataResponse);
    }


    private Path getUploadDirLocation() {
        return Paths.get(uploadDirectoryLocation).toAbsolutePath().normalize();
    }

    private String getFileContent(String fileName) throws IOException {
        Path path = Paths.get(uploadDirectoryLocation+ File.separator+fileName);
        //Path path = Paths.get(fileName);
        BufferedReader reader = Files.newBufferedReader(path);
        String data = reader.readLine();
        return data;
    }
}
