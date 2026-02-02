package com.example.service;


import com.example.model.Artist;
import com.example.repository.ArtistRepository;

// import org.springdoc.core.converters.models.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {
    @Autowired
    private ArtistRepository repository;

    public List<Artist> findAll() { return repository.findAll(); }
    public Artist save(Artist artist) { return repository.save(artist); }
    public Optional<Artist> findById(Integer id) { return repository.findById(id); }
    public List<Artist> findByName(String name, org.springframework.data.domain.Sort sort) {
        throw new UnsupportedOperationException("Unimplemented method 'findByName'");
    }
}