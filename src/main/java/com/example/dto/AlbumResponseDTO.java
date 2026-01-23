package com.example.dto;

import java.util.List;

public record AlbumResponseDTO(
    Long id,
    String title,
    List<String> coverUrls // URLs prontas para o <img src="">
) {}
