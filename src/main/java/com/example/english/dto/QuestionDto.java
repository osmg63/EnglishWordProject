package com.example.english.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
    private int wordId;
    private String terms;
    private String meanings;
    private int id;
}
