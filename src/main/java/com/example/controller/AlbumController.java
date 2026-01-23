package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Album;
import com.example.service.AlbumService;


@RestController
@RequestMapping("/artists/{artistId}/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @PostMapping
    public Album create(@PathVariable Long artistId, @RequestBody Album album) {
        return albumService.save(artistId, album);
    }
}
