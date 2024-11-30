package com.example.otp.generation.service;

import com.example.otp.generation.Exception.InvalidCredentialsException;
import com.example.otp.generation.Exception.UserAlreadyExistsException;
import com.example.otp.generation.Exception.UserNotFoundException;
import com.example.otp.generation.dto.AdminDto;
import com.example.otp.generation.dto.AdminLoginDto;
import com.example.otp.generation.entity.AdminEntity;
import com.example.otp.generation.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    // Admin Signup
    public String signup(AdminDto signupDTO) {
        // Check if username or email already exists
        if (adminRepository.existsByUsername(signupDTO.getUsername()) ||
                adminRepository.existsByEmail(signupDTO.getEmail())) {
            throw new UserAlreadyExistsException("Username or email already exists");
        }

        // Save new admin
        AdminEntity admin = new AdminEntity();
        admin.setUsername(signupDTO.getUsername());
        admin.setEmail(signupDTO.getEmail());
        admin.setPassword(signupDTO.getPassword());
        admin.setFirst_name(signupDTO.getFirst_name());
        admin.setLast_name(signupDTO.getLast_name());
        adminRepository.save(admin);

        return "Admin registered successfully";
    }

    // Admin Login
    public String login(AdminLoginDto loginDTO) {
        // Find admin by email
        AdminEntity admin = adminRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Admin with email " + loginDTO.getEmail() + " not found"));

        // Validate password
        if (admin.getPassword().equals(loginDTO.getPassword())) {
            return "User Found";
        } else {
            throw new InvalidCredentialsException("Invalid password for email " + loginDTO.getEmail());
        }
    }


}
