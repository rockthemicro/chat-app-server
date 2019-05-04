package com.example.chatappserver.controllers;

import com.example.chatappserver.entities.Message;
import com.example.chatappserver.entities.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("Duplicates")
@CrossOrigin
@RestController
public class TestController {

    @Autowired
    private MessageRepository messageRepo;

    @GetMapping("/salut")
    public String greeting(@RequestParam(value = "anything", defaultValue = "Miha") String blabla,
                           @RequestParam(value = "altparam", defaultValue = "ce sugi ma?") String costasu) {

        Message m = new Message();
        m.setContent("salut");
        m.setUserName("mama");
        m.setId(1);
        messageRepo.save(m);

        return "Hello " + blabla + "; ce faci costasule? " + costasu;
    }

    @PostMapping("iadeaici")
    public String greeting2(@RequestBody Req req) {

        return req.text;
    }
}

class Req {
    public String text;
}