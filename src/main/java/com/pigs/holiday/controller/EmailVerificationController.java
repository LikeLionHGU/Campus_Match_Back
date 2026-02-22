package com.pigs.holiday.controller;

import com.pigs.holiday.dto.EmailVerificationDto;
import com.pigs.holiday.service.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/club/email")
public class EmailVerificationController {

    private final EmailVerificationService service;

    @PostMapping("/request")
    public ResponseEntity<Void> request(@RequestBody EmailVerificationDto.RequestReqDto requestReqDto) {
        service.requestCode(requestReqDto.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/confirm")
    public ResponseEntity<EmailVerificationDto.ConfirmResDto> confirm(@RequestBody EmailVerificationDto.ConfirmReqDto confirmReqDto) {
        String token = service.confirmCode(confirmReqDto.getEmail(), confirmReqDto.getCode());
        return ResponseEntity.ok(EmailVerificationDto.ConfirmResDto.builder().verificationToken(token).build());
    }
}