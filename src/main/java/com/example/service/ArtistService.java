package com.example.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Artist;
import com.example.repository.ArtistRepository;

@Service
public class ArtistService {
    @Autowired
    private ArtistRepository repository;

    public List<Artist> findAll() { return repository.findAll(); }
    public Artist save(Artist artist) { return repository.save(artist); }
    public Optional<Artist> findById(Long id) { return repository.findById(id); }
    public List<Artist> findByName(String name, Sort sort) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByName'");
    }
}