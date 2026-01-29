package com.example.controller.v1;

import com.example.model.Regional;
import com.example.repository.RegionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/regionais")
public class RegionalController {

    @Autowired
    private RegionalRepository regionalRepository;

    @GetMapping
    public List<Regional> getAllRegionais() {
        return regionalRepository.findAll();
    }
}