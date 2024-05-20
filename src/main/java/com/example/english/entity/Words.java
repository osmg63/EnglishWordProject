package com.example.english.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Words {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String terms;
    private String meanings;
    private String meanings2;
    private String meanings3;
    private String workType;
}
