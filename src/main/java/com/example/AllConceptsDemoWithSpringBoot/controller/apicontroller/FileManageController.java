package com.example.AllConceptsDemoWithSpringBoot.controller.apicontroller;

import com.example.AllConceptsDemoWithSpringBoot.model.ApiResponse.SuccessfulFileUploadResponse;
import com.example.AllConceptsDemoWithSpringBoot.model.Dto.FileData;
import com.example.AllConceptsDemoWithSpringBoot.model.Exceptions.*;
import com.example.AllConceptsDemoWithSpringBoot.model.service.FileManageServiceDefinitions;
import com.example.AllConceptsDemoWithSpringBoot.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class FileManageController {
    // public SuccessfulFileUploadResponse uploadFile(MultipartFile file, String userName)

    @Autowired
    private FileManageServiceDefinitions fileManageServiceDefinitions;

    @PostMapping("/upload-file")
    @ResponseBody
    public Object uploadFile(@RequestParam MultipartFile file,@RequestParam String username) throws Exception {

       /* if (file.getOriginalFilename().isBlank()) {
            throw new FileNameNotFoundInRequestException("400 Bad Request - The reason is that File is Not Found in the request");
        } else if (file.isEmpty()) {
            throw new FileNameNotFoundInRequestException("400 Bad Request - The reason is that File is Not Found in the request");
        }*/

        if (file==null || file.getOriginalFilename().isBlank())
            throw new FileNameNotFoundInRequestException("400 Bad Request - The reason is that File is Not Found in the request");

        if(username==null || username.isBlank())
            throw new UserNameNotFoundInRequestException("400 Bad Request - The reason is that username parameter is Not Found in the request");

        if(!file.getOriginalFilename().contains(".txt"))
           throw new WrongFileExtensionException("400 Bad Request- The file uploaded is not a text(.txt) file");

        SuccessfulFileUploadResponse data = fileManageServiceDefinitions.uploadFile(file,username);
        return data;
    }

    @GetMapping("/findFilesBasedOnUploaderName/{uploader}")
    public List<FileData> getFilesBasedOnUploaderName(@PathVariable String uploader)
    {
        List<FileData>  fileDataList=fileManageServiceDefinitions.getFilesBasedOnUploader(uploader);
        if(fileDataList.isEmpty() && fileDataList.size()==0)
            throw new UserNameNotFoundInResponseException("404 Not Found-  The reason is the user named "+ uploader+"  is Not Found in the response file list");

        return fileDataList;
    }

    @GetMapping("/findFileBasedOnId/{fileId}")
    public Optional<FileData> getFileBasedOnId(@PathVariable String fileId)
    {
        UUID uuid=null;
        try{
             uuid = UUID.fromString(fileId);

        } catch (IllegalArgumentException exception){
            throw new InvalidFileUuidFormatException("400 Bad Request- The reason is the input file Id  "+fileId+"  is NOT in  UUID FORMAT.");
        }
        Optional<FileData> fileObject= fileManageServiceDefinitions.getFileBasedOnFileId(uuid);
        if(fileObject.isEmpty())
            throw new FileIdNotFoundInResponseException("404 Not Found- The reason is there is no matching file record present for the provided input file Id  "+fileId+"");
        return fileObject;
    }
   /*@GetMapping("/findFilesBasedOn/{fileName}")
    public List<FileData> getFilesBasedOnFileName(@PathVariable String fileName)
    {

        //return fileManageServiceDefinitions.getFilesBasedOnFileName(fileName);
    }*/
}
