package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Artist;
import com.example.service.ArtistService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.repository.ArtistRepository;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    private ArtistService service;

    @GetMapping
    public List<Artist> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Artist create(@RequestBody Artist artist) {
        return service.save(artist);
    }
}
