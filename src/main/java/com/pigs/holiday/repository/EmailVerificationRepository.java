package com.pigs.holiday.repository;

import com.pigs.holiday.domain.EmailVerification;
import com.pigs.holiday.domain.EmailVerification.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {

    Optional<EmailVerification> findTopByEmailOrderByIdDesc(String email);

    Optional<EmailVerification> findTopByEmailAndStatusOrderByIdDesc(String email, Status status);

    Optional<EmailVerification> findByEmailAndVerificationTokenAndStatus(String email, String token, Status status);
}