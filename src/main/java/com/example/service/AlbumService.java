package com.example.service;

import com.example.dto.AlbumUpdateDTO;
import com.example.model.Album;
import com.example.model.Artist;
import com.example.repository.AlbumRepository;
import com.example.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public Album save(List<Integer> artistIds, Album album) {
        List<Artist> artists = artistRepository.findAllById(artistIds);
        
        if (artists.isEmpty()) {
            throw new RuntimeException("Nenhum artista válido encontrado!");
        }

        album.setArtists(artists);

        messagingTemplate.convertAndSend("/topic/new-album", 
            "Novo álbum cadastrado: " + album.getTitle());

        return albumRepository.save(album);
    }

    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    @Transactional
    public Album update(Integer id, AlbumUpdateDTO dto) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Álbum não encontrado com ID: " + id));

        album.setTitle(dto.getTitle());

        if (dto.getArtistIds() != null && !dto.getArtistIds().isEmpty()) {
            List<Artist> artists = artistRepository.findAllById(dto.getArtistIds());
            
            if (artists.isEmpty()) {
                throw new RuntimeException("Nenhum artista válido encontrado para os IDs fornecidos!");
            }
            
            album.setArtists(artists);

            messagingTemplate.convertAndSend("/topic/albums", album);
        }

        return albumRepository.save(album);
    }
}