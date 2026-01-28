package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Sort; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    // Busca por nome (contendo parte do texto) com ordenação
    List<Artist> findByNameContainingIgnoreCase(String name, Sort sort);
}