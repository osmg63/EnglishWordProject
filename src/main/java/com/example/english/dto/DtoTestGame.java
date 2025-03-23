package com.example.english.dto;

public class DtoTestGame {
    private int id;
    private String term;
    private String word1;
    private String word2;
    private String word3;
    private String word4;

    public DtoTestGame(int id, String word4, String word3, String word2, String word1, String term) {
        this.id = id;
        this.word4 = word4;
        this.word3 = word3;
        this.word2 = word2;
        this.word1 = word1;
        this.term = term;
    }

    public DtoTestGame() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord3() {
        return word3;
    }

    public void setWord3(String word3) {
        this.word3 = word3;
    }

    public String getWord2() {
        return word2;
    }

    public void setWord2(String word2) {
        this.word2 = word2;
    }

    public String getWord1() {
        return word1;
    }

    public void setWord1(String word1) {
        this.word1 = word1;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getWord4() {
        return word4;
    }

    public void setWord4(String word4) {
        this.word4 = word4;
    }
}
