package org.w1959883.ticketing_system.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.integration.channel.DirectChannel;

@Configuration
@EnableWebSocketMessageBroker
public class MessagingTemplateConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");  // Enable simple broker to handle /topic messages
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/logs").withSockJS();  // WebSocket connection endpoint
    }

    @Bean
    public MessageChannel messageChannel() {
        return new DirectChannel();  // Define the MessageChannel
    }

    @Bean
    @Primary
    public SimpMessagingTemplate simpMessagingTemplate(MessageChannel messageChannel) {
        return new SimpMessagingTemplate(messageChannel);  // Inject the MessageChannel here
    }
}
