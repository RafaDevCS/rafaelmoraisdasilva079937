package com.example.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.JwtService;
import com.example.dto.LoginDTO;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginDTO login) {
        String token = jwtService.generateToken(login.username());
        String refresh = jwtService.generateRefreshToken(login.username());
        
        return Map.of("accessToken", token, "refreshToken", refresh);
    }

    @PostMapping("/refresh")
    public Map<String, String> refresh(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        String username = jwtService.extractUsername(refreshToken);
        String novoAccessToken = jwtService.generateToken(username);
        
        return Map.of("accessToken", novoAccessToken, "refreshToken", refreshToken);
    }
}