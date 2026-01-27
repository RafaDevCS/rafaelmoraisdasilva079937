package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.minio.MinioClient;

@ExtendWith(MockitoExtension.class)
class FileStorageServiceTest {

    @Mock
    private MinioClient minioClient;

    @InjectMocks
    private FileStorageService fileStorageService;

    @Test
    @DisplayName("Deve gerar URL assinada com sucesso")
    void shouldGeneratePresignedUrl() throws Exception {
        String fileName = "capa-rouge.jpg";
        String expectedUrl = "http://minio:9000/album-covers/capa-rouge.jpg?token=123";

        // Mockando o comportamento do MinioClient
        when(minioClient.getPresignedObjectUrl(any())).thenReturn(expectedUrl);

        String resultUrl = fileStorageService.getPresignedUrl(fileName);

        assertEquals(expectedUrl, resultUrl);
    }
}
