package com.example.chatappserver.controllers;

import com.example.chatappserver.entities.Message;
import com.example.chatappserver.entities.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;

@SuppressWarnings("Duplicates")
@CrossOrigin
@RestController
public class MessageController {

    @Autowired
    private MessageRepository messageRepo;

    @GetMapping("/api/getmessages")
    public Message[] getMessages() {
        int i = 0;
        ArrayList<Message> array = new ArrayList<>();
        Iterable<Message> iterable = messageRepo.findAll();
        Iterator<Message> iterator = iterable.iterator();

        while (iterator.hasNext()) {
            Message m = iterator.next();
            array.add(m);
        }

        Message[] result = new Message[array.size()];
        for (Message m : array) {
            result[i++] = m;
        }
        return result;
    }
}
