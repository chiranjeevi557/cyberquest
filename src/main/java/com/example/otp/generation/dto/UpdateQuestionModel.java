package com.example.otp.generation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateQuestionModel {

    private String title;

    private String description;

    private String difficulty;

    private String testCase;

    private String expectedOutput;
}
