package com.example.service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import io.minio.SetBucketPolicyArgs;
@Service
public class FileStorageService {

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;
    
    @Value("${minio.bucket.name:album-covers}")
    private String bucketName;

    @Value("${minio.external-url}")
    private String externalUrl;

    
    public String uploadFile(MultipartFile file) throws Exception {
        // Gera um nome único para o arquivo
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
        String policy = "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetBucketLocation\",\"s3:ListBucket\"],\"Resource\":[\"arn:aws:s3:::" + bucketName + "\"]},{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetObject\"],\"Resource\":[\"arn:aws:s3:::" + bucketName + "/*\"]}]}";

        minioClient.setBucketPolicy(
            SetBucketPolicyArgs.builder().bucket(bucketName).config(policy).build()
        );

        minioClient.putObject(
            PutObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(file.getContentType())
                .build()
        );
        return fileName; 
    }

    public String getPresignedUrl(String fileName) {
    try {
        MinioClient signer = MinioClient.builder()
                .endpoint(externalUrl) 
                .credentials(accessKey, secretKey)
                .build();

        return signer.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(bucketName)
                .object(fileName)
                .expiry(30, TimeUnit.MINUTES)
                .build()
        );
    } catch (Exception e) {
        throw new RuntimeException("Erro ao recuperar imagem", e);
    }
}
}
