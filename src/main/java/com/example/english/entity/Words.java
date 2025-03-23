package com.example.english.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Words {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String terms;
    private String meanings;
    private String meanings2;
    private String meanings3;
    private String workType;

    public Words() {
    }

    public Words(int id, String terms, String meanings, String meanings2, String meanings3, String workType) {
        this.id = id;
        this.terms = terms;
        this.meanings = meanings;
        this.meanings2 = meanings2;
        this.meanings3 = meanings3;
        this.workType = workType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public void setMeanings(String meanings) {
        this.meanings = meanings;
    }

    public void setMeanings2(String meanings2) {
        this.meanings2 = meanings2;
    }

    public void setMeanings3(String meanings3) {
        this.meanings3 = meanings3;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public int getId() {
        return id;
    }

    public String getTerms() {
        return terms;
    }

    public String getMeanings() {
        return meanings;
    }

    public String getMeanings2() {
        return meanings2;
    }

    public String getMeanings3() {
        return meanings3;
    }

    public String getWorkType() {
        return workType;
    }
}
