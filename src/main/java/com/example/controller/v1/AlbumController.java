package com.example.controller.v1;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dto.AlbumResponseDTO;
import com.example.dto.AlbumUpdateDTO;
import com.example.dto.AlbumCreateDTO;
import com.example.model.Album;
import com.example.model.Artist;
import com.example.model.ArtistType;
import com.example.repository.AlbumRepository;
import com.example.service.FileStorageService;
import com.example.service.AlbumService;

// import java.util.List;

@RestController
@RequestMapping("/api/v1/albums")
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private AlbumService albumService;

    @GetMapping
    public Page<AlbumResponseDTO> getAllAlbums(
            @RequestParam(required = false) ArtistType type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        // Page<Album> albumPage;
        Page<Album> albumPage = albumRepository.findAll(pageable);

        if (type != null) {
            albumPage = albumRepository.findByArtistType(type, pageable);
        } else {
            albumPage = albumRepository.findAll(pageable);
        }

        return albumPage.map(album -> new AlbumResponseDTO(
                album.getId(),
                album.getTitle(),
                album.getArtists().stream().map(Artist::getName).toList(),
                album.getCoverImages().stream()
                    .map(fileStorageService::getPresignedUrl)
                    .toList()
        ));
    }

    @PostMapping(value = "/{albumId}/covers", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Album> uploadCovers(
            @PathVariable Integer albumId, 
            @RequestPart("files") List<MultipartFile> files) throws Exception { // Mudado para List e @RequestPart
        
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new RuntimeException("Álbum não encontrado"));

        for (MultipartFile file : files) {
            String fileName = fileStorageService.uploadFile(file);
            album.getCoverImages().add(fileName);
        }

        return ResponseEntity.ok(albumRepository.save(album));
    }

    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody AlbumCreateDTO dto) {
        Album album = new Album();
        album.setTitle(dto.getTitle());
        
        Album savedAlbum = albumService.save(dto.getArtistIds(), album);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAlbum);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> update(@PathVariable Integer id, @RequestBody AlbumUpdateDTO dto) {
        Album updatedAlbum = albumService.update(id, dto);
        return ResponseEntity.ok(updatedAlbum);
    }
}