package com.example.model; // Isso indica ao Java onde o arquivo está

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer releaseYear;

    // Muitos álbuns pertencem a um artista
    @ManyToOne
    @JoinColumn(name = "artist_id")
    @JsonBackReference // Evita loop infinito no JSON
    private Artist artist;
}
