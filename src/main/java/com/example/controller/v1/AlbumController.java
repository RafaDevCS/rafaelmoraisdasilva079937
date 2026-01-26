package com.example.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.AlbumResponseDTO;
import com.example.model.Album;
import com.example.model.ArtistType;
import com.example.repository.AlbumRepository;
import com.example.service.FileStorageService;

@RestController
@RequestMapping("/api/v1/albums")
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private FileStorageService fileStorageService;

    // d) Paginação e e) Filtro por tipo de artista (SINGER/BAND)
    @GetMapping
    public Page<AlbumResponseDTO> getAllAlbums(
            @RequestParam(required = false) ArtistType type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Album> albumPage;

        if (type != null) {
            albumPage = albumRepository.findByArtistType(type, pageable);
        } else {
            albumPage = albumRepository.findAll(pageable);
        }

        // Mapeia para o DTO com URLs que expiram em 30 minutos
        return albumPage.map(album -> new AlbumResponseDTO(
                album.getId(),
                album.getTitle(),
                album.getCoverImages().stream()
                     .map(fileStorageService::getPresignedUrl)
                     .toList()
        ));
    }

    // Upload de múltiplas capas
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
