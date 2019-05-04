package com.example.chatappserver.controllers;

import com.example.chatappserver.entities.Message;
import com.example.chatappserver.entities.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.concurrent.atomic.AtomicInteger;

@Controller
@CrossOrigin
public class ChatController {

    @Autowired
    private MessageRepository messageRepo; // TODO pt docker

    AtomicInteger ai = new AtomicInteger(0);

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message chatMessage) {
        chatMessage.setId(ai.addAndGet(1));
        messageRepo.save(chatMessage);

        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Message addUser(@Payload Message chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getUserName());
        return chatMessage;
    }

}
