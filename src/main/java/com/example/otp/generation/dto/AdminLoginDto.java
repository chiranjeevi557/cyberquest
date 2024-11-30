package com.example.otp.generation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class AdminLoginDto {
    private String email;
    private String password;

    // Getters and setters...
}
