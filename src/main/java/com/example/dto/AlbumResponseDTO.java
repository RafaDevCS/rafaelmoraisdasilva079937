package com.example.dto;

import java.util.List;

public class AlbumResponseDTO {
    private Integer id; // Alterado de Long para Integer
    private String title;
    private List<String> coverUrls;

    // O construtor deve refletir a mudança
    public AlbumResponseDTO(Integer id, String title, List<String> coverUrls) {
        this.id = id;
        this.title = title;
        this.coverUrls = coverUrls;
    }

    // Getters e Setters (Certifique-se de alterar o retorno do getId para Integer)
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<String> getCoverUrls() { return coverUrls; }
    public void setCoverUrls(List<String> coverUrls) { this.coverUrls = coverUrls; }

    private List<String> artistNames; // Agora é uma lista de nomes
}