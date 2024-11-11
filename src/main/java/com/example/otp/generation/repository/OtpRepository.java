package com.example.otp.generation.repository;


import com.example.otp.generation.entity.Otp;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, String> {
    void deleteByCreatedAtBefore(LocalDateTime expiryTime);
    Optional<Otp> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE Otp o SET o.otp = '0' WHERE o.createdAt < :expiryTime")
    void updateExpiredOtps(@Param("expiryTime") LocalDateTime expiryTime);

}
