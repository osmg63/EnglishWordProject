package com.example.english.dto;

import jakarta.validation.constraints.NotEmpty;

public class DtoAIRequest {
    @NotEmpty(message = "Kelime alanı boş olamaz")
    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
