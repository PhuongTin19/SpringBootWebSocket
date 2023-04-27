package com.tinnp.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.tinnp.model.ChatMessage;

@Controller
public class WebSocketController {

	/**
	 * method sendMessage is use to send messege from client to server and  
	 * broadcast to all client connecting to  "/topic/publicChatRoom" using the SendTo method.
	 * @param chatMessage
	 * 
	 */
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/publicChatRoom")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
    	System.out.println("sendMessage");
        return chatMessage;
    }
    
    /**
     * The addUser method is used to handle adding new users to the chat room. 
     * It is called when the client sends the first message after a successful connection and 
     * is served to save the user's name into the WebSocket session.
     * @param chatMessage
     * @param headerAccessor
     * 
     */
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/publicChatRoom")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
    	System.out.println("addUser");
    	// Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
 
}
