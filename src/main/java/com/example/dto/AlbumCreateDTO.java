package com.example.dto;

import java.util.List;
import lombok.Data;

@Data
public class AlbumCreateDTO {
    private String title;
    private List<Integer> artistIds;
}