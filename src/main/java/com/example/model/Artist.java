package com.example.model; // Isso indica ao Java onde o arquivo está

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String genre;
    
    @Enumerated(EnumType.STRING)
    private ArtistType type; // SINGER ou BAND

    @ManyToMany(mappedBy = "artists")
    @JsonIgnore // Evita loop infinito no JSON
    private List<Album> albums = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "regional_id")
    private Regional regional;
}