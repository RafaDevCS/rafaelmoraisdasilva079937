package com.example.controller.v2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort; // Import essencial
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/* import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 */
import com.example.model.Artist;
import com.example.service.ArtistService;

@RestController
@RequestMapping("/api/v2/artists") // Versão 2
public class ArtistControllerV2 {
    // Digamos que na V2 você agora retorna um DTO mais enxuto
    @GetMapping
    public List<ArtistDTOV2> getAll() {
        return service.findAllV2();
    }
}
