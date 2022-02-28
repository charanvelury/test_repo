package com.example.AllConceptsDemoWithSpringBoot.model.Dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@Document(collection="FileData")
public class FileData {
    @Id
    private UUID id;
    private String fileName;
    private String fileUploadedByUser;
    private Date fileUploadTime;
    private String content;
}
