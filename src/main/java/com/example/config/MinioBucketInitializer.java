package com.example.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MinioBucketInitializer {

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucketName}")
    private String bucketName;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeBucket() {
        try {
            boolean found = minioClient.bucketExists(
                BucketExistsArgs.builder().bucket(bucketName).build()
            );
            
            if (!found) {
                minioClient.makeBucket(
                    MakeBucketArgs.builder().bucket(bucketName).build()
                );
                System.out.println("✅ MinIO: Bucket '" + bucketName + "' criado com sucesso!");
            } else {
                System.out.println("ℹ️ MinIO: Bucket '" + bucketName + "' já existe.");
            }
        } catch (Exception e) {
            System.err.println("❌ Erro ao inicializar bucket no MinIO: " + e.getMessage());
        }
    }
}