package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.model.Artist;
import com.example.model.ArtistType;
import com.example.repository.ArtistRepository;

@ExtendWith(MockitoExtension.class)
class ArtistServiceTest {

    @Mock
    private ArtistRepository repository;

    @InjectMocks
    private ArtistService service;

    @Test
    @DisplayName("Deve salvar um artista com sucesso")
    void shouldSaveArtistSuccessfully() {
        
        Artist artist = new Artist();
        artist.setName("Rouge");
        artist.setType(ArtistType.BANDA);
        
        when(repository.save(any(Artist.class))).thenReturn(artist);

        Artist savedArtist = service.save(artist);

        assertNotNull(savedArtist);
        assertEquals("Rouge", savedArtist.getName());
        verify(repository, times(1)).save(artist);
    }
}