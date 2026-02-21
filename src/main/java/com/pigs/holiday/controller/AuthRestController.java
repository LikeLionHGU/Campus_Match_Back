package com.pigs.holiday.controller;

import com.pigs.holiday.domain.Club;
import com.pigs.holiday.repository.ClubRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import com.pigs.holiday.exception.InvalidTokenException;
import com.pigs.holiday.exception.NoMatchingDataException;
import com.pigs.holiday.security.AuthService;
import com.pigs.holiday.security.ExternalProperties;
import com.pigs.holiday.security.PrincipalDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthRestController {

    final ExternalProperties externalProperties;
    final AuthService authService;

    final ClubRepository clubRepository;

    @PostMapping("")
    public ResponseEntity<Void> access(HttpServletRequest request) {
        String prefix = externalProperties.getTokenPrefix();
        String header = request.getHeader(externalProperties.getRefreshKey());

        if(header == null || !header.startsWith(prefix) || header.equals(prefix)) {
            throw new InvalidTokenException("No Prefix");
        }

        String refreshToken = header.substring(prefix.length());

        // 1) refreshToken 검증 + userId 획득
        Long userId = authService.verifyRefreshToken(refreshToken);

        // 2) 탈퇴 여부 확인 (clubRepository 필요)
        Club club = clubRepository.findById(userId).orElseThrow(() -> new InvalidTokenException("No User"));

        if (Boolean.TRUE.equals(club.getDeleted())) {
            authService.revokeRefreshToken(userId);
            throw new InvalidTokenException("Withdrawn");
        }

        String accessToken = prefix + authService.createAccessToken(userId);

        // String accessToken = prefix + authService.issueAccessToken(refreshToken);

        return ResponseEntity.status(HttpStatus.OK).header(externalProperties.getAccessKey(), accessToken).build();
    }

    @DeleteMapping("")
    public ResponseEntity<Void> logout(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        if(principalDetails == null || principalDetails.getUser() == null || principalDetails.getUser().getId() == null) {
            throw new NoMatchingDataException("No PrincipalDetails");
        }

        authService.revokeRefreshToken(principalDetails.getUser().getId());

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
