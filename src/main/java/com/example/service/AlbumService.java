package com.example.service;

import com.example.model.Album;
import com.example.model.Artist;
import com.example.repository.AlbumRepository;
import com.example.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 

import java.util.List;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    public Album save(List<Integer> artistIds, Album album) {
        List<Artist> artists = artistRepository.findAllById(artistIds);
        
        if (artists.isEmpty()) {
            throw new RuntimeException("Nenhum artista válido encontrado!");
        }

        album.setArtists(artists);

        return albumRepository.save(album);
    }

    public List<Album> findAll() {
        return albumRepository.findAll();
    }
}