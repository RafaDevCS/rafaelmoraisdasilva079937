package com.example.controller.v1;

import com.example.dto.ArtistUpdateDTO;
import com.example.model.Artist;
import com.example.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/artists")
public class ArtistController {

    @Autowired
    private ArtistService service;

    @GetMapping
    public List<Artist> getAll() {
        return service.findAll();
    }

    @GetMapping("/search")
    public List<Artist> searchByName(
    @RequestParam String name,
    @RequestParam(defaultValue = "asc") String direction) {
    
        Sort sort = direction.equalsIgnoreCase("desc") 
            ? Sort.by("name").descending() 
            : Sort.by("name").ascending();
                
        return service.findByName(name, sort);
    }

    @PostMapping
    public Artist create(@RequestBody Artist artist) {
        return service.save(artist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artist> update(@PathVariable Integer id, @RequestBody ArtistUpdateDTO dto) {
        Artist updatedArtist = service.update(id, dto);
        return ResponseEntity.ok(updatedArtist);
    }
}
