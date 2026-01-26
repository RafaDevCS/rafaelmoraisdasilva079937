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

    /**
     * Gera uma URL temporária para visualizar a imagem
     * @param fileName Nome do arquivo guardado no MinIO
     * @return URL assinada válida por 15 minutos
     */
    public String getPresignedUrl(String fileName) {
        try {
            return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(bucketName)
                    .object(fileName)
                    .expiry(30, TimeUnit.MINUTES) // <--- Alterado de 15 para 30 minutos
                    .build()
            );
        } catch (Exception e) {
            // Em caso de erro (ex: MinIO fora do ar), lançamos uma exceção clara
            throw new RuntimeException("Não foi possível gerar o link da imagem: " + fileName, e);
        }
    }
}
