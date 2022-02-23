package com.example.AllConceptsDemoWithSpringBoot.controller.apicontroller;

import com.example.AllConceptsDemoWithSpringBoot.model.ApiResponse.SuccessfulFileUploadResponse;
import com.example.AllConceptsDemoWithSpringBoot.model.Dto.FileData;
import com.example.AllConceptsDemoWithSpringBoot.model.Exceptions.FileIdNotFoundInResponseException;
import com.example.AllConceptsDemoWithSpringBoot.model.Exceptions.FileNameNotFoundInRequestException;
import com.example.AllConceptsDemoWithSpringBoot.model.Exceptions.InvalidFileUuidFormatException;
import com.example.AllConceptsDemoWithSpringBoot.model.Exceptions.UserNameNotFoundInRequestException;
import com.example.AllConceptsDemoWithSpringBoot.model.service.FileManageService;
import com.example.AllConceptsDemoWithSpringBoot.model.service.FileManageServiceDefinitions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class FileManageControllerTest {


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private FileManageServiceDefinitions fileManageService;

    @InjectMocks
    private FileManageController fileManageController;

    private String fileName="C:\\Users\\saicharan.velury\\Documents\\Process_learning_docs\\Training\\Upload\\Test\\xyz.txt";


    private FileData filedata=new FileData();
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        filedata.setFileName("abc.txt");
        filedata.setId(UUID.fromString("a8b495bf-0db5-433c-ae10-bf1cc9a3de2d"));
        filedata.setFileUploadTime(new Date());
        filedata.setFileUploadedByUser("saicharan");

    }

    @Test
    public void testUploadFile() throws Exception {
        SuccessfulFileUploadResponse successResponseData = new SuccessfulFileUploadResponse();
        successResponseData.setFileName("xyz");
        successResponseData.setFileId(UUID.fromString("09878ba6-7cd2-477d-badf-81973cb80a1b"));
        successResponseData.setFileUploadTime(new Date());
        successResponseData.setFileUploadedByUser("Saicharan");

        Mockito.when(fileManageService.uploadFile(any(), anyString())).thenReturn(successResponseData);
        String fileName = "sample-file-mock.txt";
        MockMultipartFile sampleFile = new MockMultipartFile(
                "file", fileName, "text/plain", "This is the file content".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload-file")
                        .file(sampleFile).param("username","saicharan").contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk()).andExpect(jsonPath("$.fileId").value("09878ba6-7cd2-477d-badf-81973cb80a1b"))
                .andExpect(jsonPath("$.fileName").value("xyz"));


    }

    @Test
    public void testUploadFileThrowsBadRequest_FileNotFoundInRequestException() throws Exception {
       String fileName = "";
        MockMultipartFile sampleFile = new MockMultipartFile(
                "file", fileName, "text/plain", "This is the file content".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload-file").file(sampleFile)
                        .param("username","saicharan").contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof FileNameNotFoundInRequestException))
                .andExpect(result -> assertEquals("400 Bad Request - The reason is that File is Not Found in the request", result.getResolvedException().getMessage()));
    }

    @Test
    public void testUploadFileThrowsBadRequest_UserNameNotFoundInRequestException() throws Exception {
        String fileName = "sample-file-mock.txt";
        MockMultipartFile sampleFile = new MockMultipartFile(
                "file", fileName, "text/plain", "This is the file content".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload-file").file(sampleFile)
                        .param("username","").contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UserNameNotFoundInRequestException))
                .andExpect(result -> assertEquals("400 Bad Request - The reason is that username parameter is Not Found in the request", result.getResolvedException().getMessage()));
    }

    @Test
    public void testGetFileBasedOnId() throws Exception {


        Mockito.when(fileManageService.getFileBasedOnFileId(any())).thenReturn(Optional.of(filedata));
        mockMvc.perform(MockMvcRequestBuilders.get("/findFileBasedOnId/{fileId}","a8b495bf-0db5-433c-ae10-bf1cc9a3de2d").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk()).andExpect(jsonPath("$.fileName").value("abc.txt")).andExpect(jsonPath("$.fileUploadedByUser").value("saicharan"));

    }

    @Test
    public void testGetFileBasedOnIdBadRequest_IdNotInUUIDFormatException() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/findFileBasedOnId/{fileId}","a8b495bf-0db5-43").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidFileUuidFormatException))
                .andExpect(result -> assertEquals("400 Bad Request- The reason is the input file Id  a8b495bf-0db5-43  is NOT in  UUID FORMAT.", result.getResolvedException().getMessage()));

    }

    @Test
    public void testGetFileBasedOnIdNotFound_FileIdNotFoundInResponseException() throws Exception {
        Optional<FileData> emptyFileData = Optional.empty();
        Mockito.when(fileManageService.getFileBasedOnFileId(any())).thenReturn(emptyFileData);
        mockMvc.perform(MockMvcRequestBuilders.get("/findFileBasedOnId/{fileId}", "c1bcfc50-94c5-11ec-b909-0242ac120002").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof FileIdNotFoundInResponseException))
                .andExpect(result -> assertEquals("404 Not Found- The reason is there is no matching file record present for the provided input file Id  c1bcfc50-94c5-11ec-b909-0242ac120002", result.getResolvedException().getMessage()));

    }

}
