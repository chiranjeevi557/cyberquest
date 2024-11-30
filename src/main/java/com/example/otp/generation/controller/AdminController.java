package com.example.otp.generation.controller;

import com.example.otp.generation.dto.AdminDto;
import com.example.otp.generation.dto.AdminLoginDto;
import com.example.otp.generation.response.outputResponse;
import com.example.otp.generation.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {
    @Autowired
    private AdminService adminService;

    // Admin Signup
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody AdminDto signupDTO) {
        String message = adminService.signup(signupDTO);
        return ResponseEntity.ok(new outputResponse(200,"User registered successfully!"));
    }

    // Admin Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminLoginDto loginDTO) {
        String message = adminService.login(loginDTO);
        return ResponseEntity.ok(new outputResponse(200,message));
    }
}
