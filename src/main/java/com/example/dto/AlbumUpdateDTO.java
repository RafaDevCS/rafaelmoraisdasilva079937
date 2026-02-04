package com.example.dto;

import java.util.List;
import lombok.Data;

@Data
public class AlbumUpdateDTO {
    private String title;
    private List<Integer> artistIds;
}