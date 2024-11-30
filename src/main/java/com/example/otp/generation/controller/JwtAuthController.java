package com.example.otp.generation.controller;
import com.example.otp.generation.dto.AuthRequestJwt;
import com.example.otp.generation.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class JwtAuthController {
    @Autowired
    private JwtUtil jwtUtil;

    // Static username and password
    private static final String STATIC_USERNAME = "admin";
    private static final String STATIC_PASSWORD = "password123";

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody AuthRequestJwt authRequest) {
        // Validate username and password
        if (STATIC_USERNAME.equals(authRequest.getUsername()) && STATIC_PASSWORD.equals(authRequest.getPassword())) {
            // Generate JWT token
            String token = jwtUtil.generateToken(authRequest.getUsername());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}
