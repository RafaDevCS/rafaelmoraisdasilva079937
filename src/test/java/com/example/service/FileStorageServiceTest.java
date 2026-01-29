package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.util.ReflectionTestUtils;

import io.minio.MinioClient;

@ExtendWith(MockitoExtension.class)
class FileStorageServiceTest {

    @Mock
    private MinioClient minioClient;

    @InjectMocks
    private FileStorageService fileStorageService;

    @BeforeEach
    void setUp() {
        // Isso injeta manualmente o valor que o @Value buscaria
        ReflectionTestUtils.setField(fileStorageService, "bucketName", "album-covers");
    }

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
