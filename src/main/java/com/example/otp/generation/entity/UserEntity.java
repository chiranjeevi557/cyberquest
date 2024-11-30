package com.example.otp.generation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Entity
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = true)
    private String username;

    @Column(unique = true, nullable = true)
    private String password;

    @Column(nullable = true)
    private String first_name;

    @Column(nullable = true)
    private String last_name;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private Boolean is_premium = false; // Default value is "regular"

    @Column(nullable = true)
    private int problems_solved_count = 0;

    @Column(nullable = true)
    private Boolean is_staff = false;

    @Column(nullable = true)
    private Boolean is_active = false;

    @Column(nullable = true)
    private LocalDateTime date_joined;

    // Getters and setters...
}
