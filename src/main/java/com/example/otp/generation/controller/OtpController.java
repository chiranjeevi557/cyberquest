package com.example.otp.generation.controller;



import com.example.otp.generation.Exception.Status;
import com.example.otp.generation.entity.Question;
import com.example.otp.generation.repository.QuestionRepository;
import com.example.otp.generation.response.outputResponse;
import com.example.otp.generation.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/otp")
@CrossOrigin("*")
public class OtpController {

    @Autowired
    private OtpService otpService;

    @Autowired
    private QuestionRepository questionRepository;

    private final JavaMailSender mailSender;

    public OtpController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    private final Random random = new Random();

    @PostMapping("/generate")
    public ResponseEntity<?> generateOtp(@RequestParam String email) {
        String otpCode = String.format("%06d", random.nextInt(999999));
        Boolean sendMail = sendEmail(email,otpCode);
        if(sendMail.equals(true)) {
            otpService.saveOtp(email,otpCode);
            return ResponseEntity.ok(new outputResponse(200,otpCode));
        }
         return ResponseEntity.ok(new outputResponse(500,"failed to get otp"));
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateOtp(@RequestParam String email, @RequestParam String otp) {
        boolean isValid = otpService.validateOtp(email, otp);
        if(isValid){
            return ResponseEntity.ok(new outputResponse(200,questionRepository.findAll()));
        }
        return ResponseEntity.ok(new outputResponse(401,"OTP is invalid or expired"));
    }

    public Boolean sendEmail(String email, String code) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom("yvcn11@gmail.com");
            message.setTo(email);
            message.setSubject("OTP for login");
            message.setText(code);

            mailSender.send(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

