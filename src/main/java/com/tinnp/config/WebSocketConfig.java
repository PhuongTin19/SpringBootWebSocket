package com.tinnp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.tinnp.interceptor.HttpHandshakeInterceptor;

@Configuration
@EnableWebSocketMessageBroker //Enable WebSocket Server 
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Autowired
	private HttpHandshakeInterceptor handshakeInterceptor;

	/**
	 * Register 'StompEndpoint' with path "/ws". client connection this path. it will be handle by SockJS. 
	 * method setInterceptors() is used to set HttpHandshakeInterceptor as interceptor for each handshake request.
	 * @param StompEndpointRegistry registry:
	 * 
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws").withSockJS().setInterceptors(handshakeInterceptor);
	}

	/**
	 * @param MessageBrokerRegistry registry:
	 * setApplicationDestinationPrefixes: used to prefix the application address for all addresses
	 * enableSimpleBroker: used to activate a simple message broker and set the path for registered message brokers. Here, the path "/topic" is set for the message broker.
	 * 
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/app");
		registry.enableSimpleBroker("/topic");
	}
}
