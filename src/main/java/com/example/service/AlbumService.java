package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Album;
import com.example.repository.AlbumRepository;

import com.example.model.Artist;
import com.example.repository.ArtistRepository;


@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    public Album save(Long artistId, Album album) {
        // 1. Busca o artista pelo ID
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new RuntimeException("Artista não encontrado!"));

        // 2. Vincula o artista ao álbum
        album.setArtist(artist);

        // 3. Salva o álbum
        return albumRepository.save(album);
    }

    public List<Album> findAll() {
        return albumRepository.findAll();
    }
}
