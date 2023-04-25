package com.tinnp.interceptor;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

@Component
public class HttpHandshakeInterceptor implements HandshakeInterceptor{ 
								//HandshakeInterceptor : Handle event WebSocket in process Handshake client and server
	
private static final Logger logger = LoggerFactory.getLogger(HttpHandshakeInterceptor.class);
    

	/**
	 * The beforeHandshake method is called before the WebSocket handshake is completed
	 * @param ServerHttpRequest request: represent the request handshake
	 * @param ServerHttpResponse response: represent the response handshake
	 * @param WebSocketHandler wsHandler: Handle websocket
	 * @param Map<String, Object> attributes:
	 * 
	 */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
            Map<String, Object> attributes) throws Exception {
        // logger will print a "Call beforeHandshake" message to indicate that the method has been called. 
        logger.info("Call beforeHandshake");
        
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession();
            attributes.put("sessionId", session.getId());
        }
        return true;
    }

    /*
	 * The beforeHandshake method is called after the WebSocket handshake is completed
	 * @param ServerHttpRequest request: represent the request handshake
	 * @param ServerHttpResponse response: represent the response handshake
	 * @param WebSocketHandler wsHandler: Handle websocket
	 * @param Exception ex:
	 * 
	 */
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
            Exception ex) {
        logger.info("Call afterHandshake");
    }

}
