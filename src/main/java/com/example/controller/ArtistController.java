package com.example.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort; // Import essencial
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.model.Artist;
import com.example.service.ArtistService;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    private ArtistService service;

    @GetMapping("/search")
    public List<Artist> searchByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "asc") String direction) {
        
        Sort sort = direction.equalsIgnoreCase("desc") 
                    ? Sort.by("name").descending() 
                    : Sort.by("name").ascending();
                    
        return service.findByName(name, sort);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artist> update(@PathVariable Long id, @RequestBody Artist artistDetails) {
        return service.findById(id)
                .map(artist -> {
                    artist.setName(artistDetails.getName());
                    artist.setGenre(artistDetails.getGenre());
                    artist.setType(artistDetails.getType()); // Certifique-se que o model tem o campo 'type'
                    return ResponseEntity.ok(service.save(artist));
                }).orElse(ResponseEntity.notFound().build());
    }
}