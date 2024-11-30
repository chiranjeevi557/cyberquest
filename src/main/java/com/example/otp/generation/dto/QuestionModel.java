package com.example.otp.generation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionModel {
    @NonNull
    private String title;
    @NonNull
    private String description;
    @NonNull
    private String difficulty;
    @NonNull
    private String testCase;
    @NonNull
    private String expectedOutput;
}
