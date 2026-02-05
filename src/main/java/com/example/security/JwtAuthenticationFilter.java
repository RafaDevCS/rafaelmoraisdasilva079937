package com.example.security;

import com.example.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // 1. Pega o cabeçalho "Authorization"
        final String authHeader = request.getHeader("Authorization");

        // 2. Verifica se o cabeçalho existe e começa com "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Extrai o token (remove a palavra "Bearer ")
        String jwt = authHeader.substring(7);
        String username = jwtService.extractUsername(jwt);

        // 4. Se encontrou o usuário e ele ainda não está autenticado no Spring
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            
            // 5. Valida o token
            if (jwtService.isTokenValid(jwt, username)) {
                // Cria o objeto de autenticação (simplificado com lista vazia de permissões)
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        username, null, Collections.emptyList()
                );
                
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                // 6. Define o usuário como "Autenticado" no contexto do Spring
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 7. Continua a requisição
        filterChain.doFilter(request, response);
    }
}