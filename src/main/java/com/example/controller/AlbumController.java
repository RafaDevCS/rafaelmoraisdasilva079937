package com.example.controller;

import com.example.model.Album;
import com.example.model.ArtistType; // Seu Enum
import com.example.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page; // IMPORTANTE
import org.springframework.data.domain.PageRequest; // IMPORTANTE
import org.springframework.data.domain.Pageable; // IMPORTANTE
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @GetMapping
    public Page<Album> getAllAlbums(
            @RequestParam(required = false) ArtistType type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        
        if (type != null) {
            return albumRepository.findByArtistType(type, pageable);
        }
        return albumRepository.findAll(pageable);
    }
}