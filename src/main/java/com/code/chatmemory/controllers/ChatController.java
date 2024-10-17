package com.code.chatmemory.controllers;

import com.code.chatmemory.models.ChatRequest;
import com.code.chatmemory.models.ChatResponse;
import com.code.chatmemory.services.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final OpenAIService openAIService;

    public ChatController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/chat-memory")
    public ChatResponse getChatCompletion(@RequestBody ChatRequest chatRequest) {
        return openAIService.getChatCompletion(chatRequest);
    }
}
