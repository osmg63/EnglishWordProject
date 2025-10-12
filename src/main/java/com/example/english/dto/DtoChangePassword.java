package com.example.english.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class DtoChangePassword {
    private int id;
    private String oldPassword;

    @Length(min = 6,message = "en az 6 karakter olmalıdır.")
    @Pattern(regexp = ".*[A-Z].*", message = "Şifre en az 1 büyük harf içermeli!")
    private String newPassword;
}
