package com.code.chatmemory.controllers;

import com.code.chatmemory.models.ChatRequest;
import com.code.chatmemory.models.ChatResponse;
import com.code.chatmemory.models.Message;
import com.code.chatmemory.services.OpenAIService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private OpenAIService openAIService;

    @PostMapping("/chat-memory")
    public ChatResponse getChatCompletion(@RequestBody ChatRequest chatRequest, HttpSession session) {
        // Lấy lịch sử cuộc trò chuyện từ session
        List<Message> conversationHistory = (List<Message>) session.getAttribute("conversationHistory");
        if (conversationHistory == null) {
            conversationHistory = new ArrayList<>();
        }
        // Thêm tin nhắn mới của người dùng vào lịch sử
        conversationHistory.addAll(chatRequest.getMessages());
        // Tạo yêu cầu mới với toàn bộ lịch sử cuộc trò chuyện
        ChatRequest fullChatRequest = new ChatRequest();
        fullChatRequest.setModel(chatRequest.getModel());
        fullChatRequest.setMessages(conversationHistory);
        // Gọi OpenAIService để lấy phản hồi
        ChatResponse chatResponse = openAIService.getChatCompletion(fullChatRequest);
        // Thêm phản hồi của assistant vào lịch sử
        Message assistantMessage = chatResponse.getChoices().getFirst().getMessage();
        conversationHistory.add(assistantMessage);

        // Lưu lại lịch sử cuộc trò chuyện vào session
        session.setAttribute("conversationHistory", conversationHistory);
        return chatResponse;
    }
}
