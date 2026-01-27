package com.example.config;

import org.springframework.context.annotation.Configuration;

/* import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

   /*  @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Ativa um broker simples na memória para enviar mensagens ao front
        config.enableSimpleBroker("/topic");
        // Prefixo para mensagens enviadas do front para o back (opcional aqui)
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Endpoint que o front-end usará para se conectar (ex: ws://localhost:8080/ws-music)
        registry.addEndpoint("/ws-music")
                .setAllowedOrigins("*"); // Em produção, restrinja para o domínio do seu front
    } */
}