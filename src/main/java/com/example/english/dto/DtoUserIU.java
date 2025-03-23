package com.example.english.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class DtoUserIU {
    @Length(min = 3,message = "en az 3 karakter olmalı")
    @Length(max = 15,message = "en fazla 15 karakter olabilir")
    @NotEmpty
    private String name;
    @Length(min = 3,message = "en az 3 karakter olmalı")
    @Length(max = 15,message = "en fazla 15 karakter olabilir")
    @NotEmpty
    private String lastName;
    @Length(min = 3,message = "en az 3 karakter olmalı")
    @Length(max = 15,message = "en fazla 15 karakter olabilir")
    @NotEmpty
    private String username;
    @Length(min = 6,message = "en az 6 karakter olmalıdır.")
    @Pattern(regexp = ".*[A-Z].*", message = "Şifre en az 1 büyük harf içermeli!")
    private String password;
    private int point;

    public DtoUserIU(String name, String lastName, String username, String password, int point) {
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.point = point;

    }

    public DtoUserIU() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
