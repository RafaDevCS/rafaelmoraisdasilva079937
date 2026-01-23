package com.example.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;

@Service
public class FileStorageService {

    @Autowired private MinioClient minioClient;
    @Value("${minio.bucketName}") private String bucketName;

    public String uploadFile(MultipartFile file) throws Exception {
        // Gera um nome único para o arquivo
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
        
        minioClient.putObject(
            PutObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(file.getContentType())
                .build()
        );
        return fileName; // Retornamos o nome para salvar no banco
    }
}
