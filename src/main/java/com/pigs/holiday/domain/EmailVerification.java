package com.pigs.holiday.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class EmailVerification extends AuditingFields {

    @Column(nullable = false, length = 320)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status;

    @Column(nullable = false, length = 120)
    private String codeHash;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @Column(nullable = false)
    private int attemptCount;

    @Column(nullable = false)
    private int sendCount;

    private LocalDateTime lastSentAt;

    @Column(length = 64)
    private String verificationToken;

    private LocalDateTime verifiedAt;

    public enum Status { PENDING, VERIFIED, USED, EXPIRED }
}