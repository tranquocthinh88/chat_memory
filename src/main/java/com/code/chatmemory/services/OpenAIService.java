package com.code.chatmemory.services;

import com.code.chatmemory.models.ChatRequest;
import com.code.chatmemory.models.ChatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenAIService {
    @Value("${spring.ai.openai.api-key}")
    private String openaiApiKey;
    @Value("${spring.ai.openai.chat.base-url}")
    private String url;

    public ChatResponse getChatCompletion(ChatRequest chatRequest) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openaiApiKey);

        HttpEntity<ChatRequest> requestEntity = new HttpEntity<>(chatRequest, headers);

        ResponseEntity<ChatResponse> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                ChatResponse.class
        );

        return responseEntity.getBody();
    }
}
