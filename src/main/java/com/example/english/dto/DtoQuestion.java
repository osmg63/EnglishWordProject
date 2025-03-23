package com.example.english.dto;



public class DtoQuestion {
    private int id;
    private int wordId;
    private String meanings;

    public DtoQuestion() {
    }

    public DtoQuestion(int id, int wordId, String meanings) {
        this.id = id;
        this.wordId = wordId;
        this.meanings = meanings;
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeanings() {
        return meanings;
    }

    public void setMeanings(String meanings) {
        this.meanings = meanings;
    }
}
