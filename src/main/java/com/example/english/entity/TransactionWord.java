package com.example.english.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class TransactionWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "word_id")
    private Words word;

    public TransactionWord(int id, User user, Words word, LocalDateTime date, Boolean know) {
        this.id = id;
        this.user = user;
        this.word = word;
        this.date = date;
        this.know = know;
    }

    public TransactionWord() {
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Words getWord() {
        return word;
    }

    public void setWord(Words word) {
        this.word = word;
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

    private LocalDateTime date;
    private Boolean know;


}
