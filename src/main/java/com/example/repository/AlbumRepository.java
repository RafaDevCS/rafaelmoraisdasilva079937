package com.example.repository;

// import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Pageable; 
import org.springframework.data.domain.Page;     
// import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Album;
import com.example.model.ArtistType;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
    // d) Paginação e e) Filtro por tipo de artista
    // Page<Album> findByArtistType(ArtistType type, Pageable pageable);
    @Query("SELECT a FROM Album a JOIN a.artists art WHERE art.type = :type")
    Page<Album> findByArtistType(@Param("type") ArtistType type, Pageable pageable);
    
    // Apenas paginação simples
    Page<Album> findAll(Pageable pageable);
}