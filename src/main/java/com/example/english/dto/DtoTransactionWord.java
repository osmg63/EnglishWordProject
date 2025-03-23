package com.example.english.dto;

import java.time.LocalDateTime;

public class DtoTransactionWord {

    private int id;
    private int userId;
    private int wordId;
    private LocalDateTime date;
    private Boolean know;

    // Default constructor
    public DtoTransactionWord() {}

    // Parametrized constructor
    public DtoTransactionWord(int id, int userId, int wordId, LocalDateTime date, Boolean know) {
        this.id = id;
        this.userId = userId;
        this.wordId = wordId;
        this.date = date;
        this.know = know;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Boolean getKnow() {
        return know;
    }

    public void setKnow(Boolean know) {
        this.know = know;
    }
}
