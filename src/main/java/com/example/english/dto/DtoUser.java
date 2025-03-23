package com.example.english.dto;


public class DtoUser {
    private int id;
    private String name;
    private String lastName;
    private String username;
    private int point=0;

    public DtoUser(int id, String username, String lastName, String name, int testNumber, int point) {
        this.id = id;
        this.username = username;
        this.lastName = lastName;
        this.name = name;
        this.point = point;
    }

    public DtoUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }


}
