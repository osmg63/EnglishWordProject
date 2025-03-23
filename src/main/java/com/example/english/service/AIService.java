package com.example.english.service;

import com.example.english.exception.BaseException;
import com.example.english.exception.ErrorMessage;
import com.example.english.exception.MessageType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
public class AIService {
    @Value("${gemini.api.key}")
    private String geminiApiKey;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    private final WebClient webClient;

    public AIService(WebClient.Builder webClient) {
        this.webClient = webClient.build();
    }

    public String getAnswer(String question) {
        try{
            question = question+"kelimesiyle ilgili ingilizce bir cümle kur ve türkçe anlamını yaz iki cümle alt alta olsun başka hiç bir açıklama yapma ";
            Map<String, Object> requestBody = Map.of("contents", new Object[]{
                    Map.of("parts",new Object[]{
                            Map.of("text", question),
                    })
            });

            String response= webClient.post()
                    .uri(geminiApiUrl + geminiApiKey)
                    .header("Content-Type","application/json")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();



            return response;
        }catch (Exception e){
            throw new BaseException(new ErrorMessage(MessageType.AI_FAILED));
        }

    }
}
