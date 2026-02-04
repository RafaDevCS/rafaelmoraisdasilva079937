package com.example.service;


import com.example.dto.ArtistUpdateDTO;
import com.example.model.Artist;
import com.example.model.Regional;
import com.example.repository.ArtistRepository;
import com.example.repository.RegionalRepository;

// import org.springdoc.core.converters.models.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {
    
    @Autowired
    private ArtistRepository repository;

    @Autowired
    private RegionalRepository regionalRepository;

    @Transactional
    public Artist update(Integer id, ArtistUpdateDTO dto) {
        // 1. Busca o artista atual
        Artist artist = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artista não encontrado com ID: " + id));

        // 2. Atualiza os campos básicos
        artist.setName(dto.getName());
        artist.setGenre(dto.getGenre());
        artist.setType(dto.getType());

        // 3. Atualiza a Regional se um ID foi enviado
        if (dto.getRegionalId() != null) {
            Regional regional = regionalRepository.findById(dto.getRegionalId())
                    .orElseThrow(() -> new RuntimeException("Regional não encontrada"));
            artist.setRegional(regional);
        }

        return repository.save(artist);
    }
    
    public List<Artist> findAll() { 
        return repository.findAll(); 
    }
    
    public Artist save(Artist artist) { 
        return repository.save(artist); 
    }

    public Optional<Artist> findById(Integer id) { 
        return repository.findById(id); 
    }
    
    public List<Artist> findByName(String name, org.springframework.data.domain.Sort sort) {
        throw new UnsupportedOperationException("Unimplemented method 'findByName'");
    }
}