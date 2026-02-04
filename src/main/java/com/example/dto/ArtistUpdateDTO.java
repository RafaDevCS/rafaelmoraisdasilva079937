package com.example.dto;

import lombok.Data;

@Data
public class ArtistUpdateDTO {
    private String name;
    private String genre;
    private String type;
    private Integer regionalId; 
}