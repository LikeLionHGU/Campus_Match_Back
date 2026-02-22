package com.pigs.holiday.service;

import com.pigs.holiday.domain.EmailVerification;
import com.pigs.holiday.domain.EmailVerification.Status;
import com.pigs.holiday.repository.EmailVerificationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {

    private final EmailVerificationRepository repo;
    private final JavaMailSender mailSender;
    private final BCryptPasswordEncoder encoder;

    private static final SecureRandom rnd = new SecureRandom();
    private static final int EXPIRE_MIN = 5;
    private static final int MAX_ATTEMPT = 5;
    private static final int RESEND_SEC = 60;

    @Transactional
    public void requestCode(String email) {
        String e = normalize(email);

        repo.findTopByEmailOrderByIdDesc(e).ifPresent(prev -> {
            if (prev.getLastSentAt() != null) {
                LocalDateTime allowed = prev.getLastSentAt().plusSeconds(RESEND_SEC);
                if (LocalDateTime.now().isBefore(allowed)) {
                    throw new IllegalStateException("Too many requests.");
                }
            }
        });

        String code = gen6();
        String hash = encoder.encode(code);

        EmailVerification ev = new EmailVerification();
        ev.setEmail(e);
        ev.setStatus(Status.PENDING);
        ev.setCodeHash(hash);
        ev.setExpiresAt(LocalDateTime.now().plusMinutes(EXPIRE_MIN));
        ev.setAttemptCount(0);
        ev.setSendCount(1);
        ev.setLastSentAt(LocalDateTime.now());

        repo.save(ev);

        sendMail(e, code, ev.getExpiresAt());
    }

    @Transactional
    public String confirmCode(String email, String code) {
        String e = normalize(email);

        EmailVerification ev = repo.findTopByEmailAndStatusOrderByIdDesc(e, Status.PENDING)
                .orElseThrow(() -> new IllegalStateException("No pending request."));

        if (LocalDateTime.now().isAfter(ev.getExpiresAt())) {
            ev.setStatus(Status.EXPIRED);
            throw new IllegalStateException("Expired.");
        }

        ev.setAttemptCount(ev.getAttemptCount() + 1);
        if (ev.getAttemptCount() > MAX_ATTEMPT) {
            ev.setStatus(Status.EXPIRED);
            throw new IllegalStateException("Too many attempts.");
        }

        if (!encoder.matches(code, ev.getCodeHash())) {
            throw new IllegalStateException("Wrong code.");
        }

        ev.setStatus(Status.VERIFIED);
        ev.setVerifiedAt(LocalDateTime.now());
        ev.setVerificationToken(UUID.randomUUID().toString());

        return ev.getVerificationToken();
    }

    @Transactional
    public void consumeToken(String email, String verificationToken) {
        String e = normalize(email);

        EmailVerification ev = repo.findByEmailAndVerificationTokenAndStatus(e, verificationToken, Status.VERIFIED)
                .orElseThrow(() -> new IllegalStateException("Invalid verification token."));

        ev.setStatus(Status.USED);
    }

    private String gen6() {
        return String.format("%06d", rnd.nextInt(1_000_000));
    }

    private String normalize(String email) {
        if (email == null) throw new IllegalArgumentException("email required");
        return email.trim().toLowerCase();
    }

    private void sendMail(String to, String code, LocalDateTime expiresAt) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject("[Campus Match] 이메일 인증 코드");
        msg.setText("인증 코드: " + code + "\n만료 시간: " + expiresAt);
        mailSender.send(msg);
    }
}