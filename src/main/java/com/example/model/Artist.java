package com.example.model; // Isso indica ao Java onde o arquivo está

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String genre;

    // Um artista tem muitos álbuns
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    @JsonManagedReference // Evita loop infinito no JSON
    private List<Album> albums;
}