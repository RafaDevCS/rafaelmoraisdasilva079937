package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
// @EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Desabilita CSRF para testar POST/PUT
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); // Libera tudo
        return http.build();
    }
/* 
    private final JwtAuthenticationFilter jwtAuthFilter;

    // Construtor para injetar o filtro
    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desativa para APIs REST
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // API sem estado (Stateless)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/actuator/**").permitAll() // Libera para monitoramento
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                .requestMatchers("/api/v1/**").authenticated() // Protege todos os endpoints v1
                .anyRequest().authenticated()
            )
            // Adiciona nosso filtro JWT antes do filtro padrão de login
            //.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            // Permite carregar o console do H2 em um frame
            .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    } */
}