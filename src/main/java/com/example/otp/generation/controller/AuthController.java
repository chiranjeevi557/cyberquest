package com.example.otp.generation.controller;

import com.example.otp.generation.Exception.Status;
import com.example.otp.generation.dto.LoginDto;
import com.example.otp.generation.dto.SignupDto;
import com.example.otp.generation.entity.Question;
import com.example.otp.generation.entity.UserEntity;
import com.example.otp.generation.response.outputResponse;
import com.example.otp.generation.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    //  private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupDto signupRequest) {
           String message = userService.registerUser(signupRequest);
            return ResponseEntity.ok(new outputResponse(200,message));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginRequest) throws Status {
       // userService.validateUser(loginRequest.getEmail(),loginRequest.getPassword());
        String data = userService.validateUser(loginRequest.getEmail(),loginRequest.getPassword());

        return ResponseEntity.ok(new outputResponse(200,data));

    }

    @GetMapping("/profile/{email}")
    public ResponseEntity<UserEntity> getProfile(@PathVariable String email) throws Exception {
        UserEntity userEntity = userService.get_Profile(email);

        return ResponseEntity.ok(userEntity);
    }

}
