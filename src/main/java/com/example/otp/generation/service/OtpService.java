package com.example.otp.generation.service;

import com.example.otp.generation.entity.Otp;
import com.example.otp.generation.repository.OtpRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OtpService {
    @Autowired
    private OtpRepository otpRepository;

    public boolean validateOtp(String email, String inputOtp) {
        Optional<Otp> otpRecord = otpRepository.findByEmail(email);

        // Check if OTP exists and matches the input OTP
        if (otpRecord.isPresent() && otpRecord.get().getOtp().equals(inputOtp)) {
            return true;
        }
        return false; // OTP is either invalid or doesn't exist
    }
    @Scheduled(fixedRate = 60000) // runs every minute
    @Transactional
    public void deleteExpiredOtps() {

        LocalDateTime expiryTime = LocalDateTime.now().minusMinutes(3);
        otpRepository.updateExpiredOtps(expiryTime);
    }
    public void saveOtp(String email, String otpCode) {
        // Check if an OTP record already exists for this email
        Optional<Otp> existingOtp = otpRepository.findByEmail(email);

        if (existingOtp.isPresent()) {
            // Update existing OTP
            Otp otp = existingOtp.get();
            otp.setOtp(otpCode);
            otp.setCreatedAt(LocalDateTime.now());
            otpRepository.save(otp);
        } else {
            // Create a new OTP record
            Otp otp = new Otp();
            otp.setEmail(email);
            otp.setOtp(otpCode);
            otp.setCreatedAt(LocalDateTime.now());
            otpRepository.save(otp);
        }
}
}
