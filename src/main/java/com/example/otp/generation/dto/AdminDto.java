package com.example.otp.generation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max =20, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "first_name cannot be blank")
    @Size(min = 1, max =20, message = "fist_name must be between 3 and 50 characters")
    private String first_name;

    @NotBlank(message = "last_name cannot be blank")
    @Size(min = 1, max =20, message = "last_name must be between 3 and 50 characters")
    private String last_name;

    @NotBlank(message = "password cannot be blank")
    @Size(min = 10, max =20, message = "password must be between 3 and 50 characters")
    private String password;
}
