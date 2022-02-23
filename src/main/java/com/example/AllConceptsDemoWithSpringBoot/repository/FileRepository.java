package com.example.AllConceptsDemoWithSpringBoot.repository;

import com.example.AllConceptsDemoWithSpringBoot.model.Dto.FileData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface FileRepository extends MongoRepository<FileData, UUID> {

        List<FileData> findByFileUploadedByUser(String fileUploadedByUser);
        Optional<FileData> findById(UUID id);
}
