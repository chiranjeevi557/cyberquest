package com.example.otp.generation.controller;



import com.example.otp.generation.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Random;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    @Autowired
    private OtpService otpService;

    private final JavaMailSender mailSender;

    public OtpController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    private final Random random = new Random();

    @PostMapping("/generate")
    public String generateOtp(@RequestParam String email) {
        String otpCode = String.format("%06d", random.nextInt(999999));
        Boolean sendMail = sendEmail(email,otpCode);
        if(sendMail.equals(true)) {
            otpService.saveOtp(email,otpCode);
            return "OTP for user " + ": " + otpCode;
        }
         return "Failed";
    }

    @PostMapping("/validate")
    public String validateOtp(@RequestParam String email, @RequestParam String otp) {
        boolean isValid = otpService.validateOtp(email, otp);
        return isValid ? "OTP is valid" : "OTP is invalid or expired";
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

