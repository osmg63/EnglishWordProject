package com.example.english.service;

import com.example.english.exception.BaseException;
import com.example.english.exception.ErrorMessage;
import com.example.english.exception.MessageType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

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
            question = """
                        Sen bir İngilizce öğretmenisin.
                        Kullanıcı sana bir kelime verir.
                        Sadece şu kurallara uyan bir yanıt oluştur:
                        1. Önce o kelimeyle anlamlı bir İngilizce cümle kur.
                        2. Yeni satıra geçerek Türkçe anlamını yaz.
                        3. Çıktı şu biçimde olmalı:
                        
                        İngilizce: (cümle)
                        Türkçe: (çeviri)
                        
                        4. Başka hiçbir şey yazma. Açıklama, not, emoji, işaret, yıldız veya markdown karakteri kullanma.
                        Kullanıcının kelimesi: %s
                        """.formatted(question);
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
            System.out.println(e);
            throw new BaseException(new ErrorMessage(MessageType.AI_FAILED));
        }

    }
    public String createParagraph(String question) {
        try{
            question =
                    """
                            Sen bir İngilizce öğretmenisin.
                            Kullanıcı sana bir veya birkaç kelime verir.
                            Bu kelimelerle ilgili anlamlı, akılda kalıcı bir paragraf yaz.
                            Kurallar:
                            1. Paragraf en az 3, en fazla 5 cümle içermelidir.
                            2. Paragraf İngilizce olmalıdır.
                            3. Altına satır satır Türkçe çevirisini yaz.
                            4. Çıktı sadece şu biçimde olmalıdır:
                    
                            İngilizce:
                            (3-5 cümlelik İngilizce paragraf)
                    
                            Türkçe:
                            (paragrafın satır satır Türkçe çevirisi)
                    
                            5. Markdown, yıldız, tire, numara, emoji veya açıklama ekleme.
                            6. Başka hiçbir şey yazma.
                    
                            Kullanıcının verdiği kelimeler: %s
                            """.formatted(question);
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
