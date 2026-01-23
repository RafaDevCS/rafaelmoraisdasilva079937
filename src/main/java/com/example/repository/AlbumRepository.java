package com.example.repository;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Album;
import com.example.model.ArtistType;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    // d) Paginação e e) Filtro por tipo de artista
    Page<Album> findByArtistType(ArtistType type, org.springframework.data.domain.Pageable pageable);
    
    // Apenas paginação simples
    Page<Album> findAll(Pageable pageable);
}