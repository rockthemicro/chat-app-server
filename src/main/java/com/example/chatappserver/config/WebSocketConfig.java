package com.example.chatappserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins("*");
    }

    /*
    The first line defines that the messages whose destination starts with “/app” should be routed to
    message-handling methods (we’ll define these methods shortly), i.e. methods annotated
    with @MessageMapping
    For example, a message with destination /app/chat.sendMessage will be routed to the sendMessage() method,
    and a message with destination /app/chat.addUser will be routed to the addUser() method.

    And, the second line defines that the messages whose destination starts with “/topic” should be routed to
    the message broker. Message broker broadcasts messages to all the connected clients who are subscribed to
    a particular topic.
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }
}
