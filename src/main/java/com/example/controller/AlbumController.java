package com.example.controller;

import com.example.model.Album;
import com.example.model.ArtistType; // Seu Enum
import com.example.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page; // IMPORTANTE
import org.springframework.data.domain.PageRequest; // IMPORTANTE
import org.springframework.data.domain.Pageable; // IMPORTANTE
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.service.FileStorageService;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private FileStorageService fileStorageService;

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

    @PostMapping("/{albumId}/covers")
    public ResponseEntity<Album> uploadCovers(
            @PathVariable Long albumId,
            @RequestParam("files") MultipartFile[] files) throws Exception {
        
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new RuntimeException("Álbum não encontrado"));

        for (MultipartFile file : files) {
            String fileName = fileStorageService.uploadFile(file);
            album.getCoverImages().add(fileName);
        }

        return ResponseEntity.ok(albumRepository.save(album));
    }
}