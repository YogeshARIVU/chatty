package com.example.ChatApp.controller;

import com.example.ChatApp.model.Message;
import com.example.ChatApp.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/messages")
    public List<Message> getMessages() {
        return chatService.getAllMessages();
    }

    @PostMapping("/message")
    public Message sendMessage(@RequestBody Message message) {
        return chatService.saveMessage(message);
    }
}
