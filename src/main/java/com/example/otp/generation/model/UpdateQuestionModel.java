package com.example.otp.generation.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UpdateQuestionModel {

    private String title;

    private String description;

    private String difficulty;

    private String testCase;

    private String expectedOutput;
}
