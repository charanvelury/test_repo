package com.example.AllConceptsDemoWithSpringBoot.service;

import com.example.AllConceptsDemoWithSpringBoot.model.ApiResponse.FileBasedOnIdResponse;
import com.example.AllConceptsDemoWithSpringBoot.model.ApiResponse.FileDataResponse;
import com.example.AllConceptsDemoWithSpringBoot.model.ApiResponse.SuccessfulFileUploadResponse;
import com.example.AllConceptsDemoWithSpringBoot.model.Dto.FileData;
import com.example.AllConceptsDemoWithSpringBoot.model.service.FileManageServiceDefinitions;
import com.example.AllConceptsDemoWithSpringBoot.repository.FileRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "/application-test.properties")
@SpringBootTest
public class FileManageServiceDefinitionsTest {

    @Mock
    private FileRepository fileRepo;

    @Value("${project.file.test.upload.filename}")
    private String testUploadFileName;

    @InjectMocks
    private FileManageServiceDefinitions fileManageServiceDefinitions;

    private String fileName="C:\\Users\\saicharan.velury\\Documents\\Process_learning_docs\\Training\\Upload\\Test\\xyz.txt";;

   /* @Before
    public void assignFileName()
    {
        fileName="C:\\Users\\saicharan.velury\\Documents\\Process_learning_docs\\Training\\Upload\\Test\\xyz.txt";
    }*/

    @Test
    void testUploadFileService() throws Exception{
          fileName="C:\\Users\\saicharan.velury\\Documents\\Process_learning_docs\\Training\\Upload\\Test\\xyz.txt";
        File file=new File(fileName);
        FileInputStream fis=new FileInputStream(file);
        MockMultipartFile multipartFile = new MockMultipartFile(
                "attachments", file.getName(),
                MediaType.MULTIPART_FORM_DATA_VALUE,
                fis);
        FileData resultSavedObject= new FileData();

        resultSavedObject.setFileName(fileName);
        resultSavedObject.setId(UUID.fromString("09878ba6-7cd2-477d-badf-81973cb80a1b"));
        resultSavedObject.setFileUploadTime(new Date());
        resultSavedObject.setFileUploadedByUser("Saicharan");
        resultSavedObject.setContent("ABC Content");
        when(fileRepo.save(any())).thenReturn(resultSavedObject);

        SuccessfulFileUploadResponse response=fileManageServiceDefinitions.uploadFile(multipartFile,"Saicharan");

        assertEquals("09878ba6-7cd2-477d-badf-81973cb80a1b",response.getId().toString());
    }

    @Test
    public void testGetFilesBasedOnUploader()
    {
        FileData resultObject= new FileData();
        List<FileData> expectedResultList=new ArrayList<FileData>();
        resultObject.setFileName(fileName);
        resultObject.setId(UUID.fromString("09878ba6-7cd2-477d-badf-81973cb80a1b"));
        resultObject.setFileUploadTime(new Date());
        resultObject.setFileUploadedByUser("Saicharan");
        expectedResultList.add(resultObject);
        when(fileRepo.findByFileUploadedByUser(anyString())).thenReturn(expectedResultList);
         List<FileDataResponse> actualResultList = fileManageServiceDefinitions.getFilesBasedOnUploader("Saicharan");
         assertEquals(expectedResultList.size(),actualResultList.size());
         assertEquals(expectedResultList.get(0).getId(),actualResultList.get(0).getId());
    }

    @Test
    public void testGetFileBasedOnFileId()
    {
        FileData expectedResultObject= new FileData();
      //  List<FileData> expectedResultList=new ArrayList<FileData>();
        expectedResultObject.setFileName(fileName);
        expectedResultObject.setId(UUID.fromString("09878ba6-7cd2-477d-badf-81973cb80a1b"));
        expectedResultObject.setFileUploadTime(new Date());
        expectedResultObject.setFileUploadedByUser("Saicharan");
       // expectedResultList.add(resultObject);
        when(fileRepo.findById(any())).thenReturn(Optional.of(expectedResultObject));
        Optional<FileBasedOnIdResponse> actualResultFileObject = fileManageServiceDefinitions.getFileBasedOnFileId(UUID.fromString("09878ba6-7cd2-477d-badf-81973cb80a1b"));

        assertEquals(expectedResultObject.getFileName(),expectedResultObject.getFileName());
    }

}
