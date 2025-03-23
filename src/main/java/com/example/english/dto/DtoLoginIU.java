package com.example.english.dto;

import jakarta.validation.constraints.NotEmpty;

public class DtoLoginIU {
    @NotEmpty(message = "Password Boş olamaz!!")
    private String password;
    @NotEmpty(message = "Kullanıcı adı boş olamaz!!")
    private String username;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public DtoLoginIU(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public DtoLoginIU() {
    }


}
