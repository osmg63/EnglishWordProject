package com.example.english.controller;

import com.example.english.dto.DtoAIRequest;
import com.example.english.service.AIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class AIController {
    private final AIService service;



    @PostMapping("/createSentence")
    public ResponseEntity<String> askQuestion(@RequestBody @Valid DtoAIRequest word) {
        String answer = service .getAnswer(word.getWord());
        return ResponseEntity.ok(answer);

    }

}
