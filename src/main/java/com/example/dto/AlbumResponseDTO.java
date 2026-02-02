package com.example.dto;

import java.util.List;

public class AlbumResponseDTO {
    private Integer id; 
    private String title;
    private List<String> artistNames;
    private List<String> coverUrls;

    public AlbumResponseDTO(Integer id, String title, List<String> artistNames, List<String> coverUrls) {
        this.id = id;
        this.title = title;
        this.artistNames = artistNames;
        this.coverUrls = coverUrls;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<String> getCoverUrls() { return coverUrls; }
    public void setCoverUrls(List<String> coverUrls) { this.coverUrls = coverUrls; }

    public List<String> getArtistNames() { return artistNames; }
    public void setArtistNames(List<String> artistNames) { this.artistNames = artistNames; } 
}