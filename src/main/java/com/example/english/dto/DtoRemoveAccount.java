package com.example.english.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class DtoRemoveAccount {
    private int id;
    private String password;
}
