package com.pigs.holiday.dto;

import lombok.*;

public class EmailVerificationDto {

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class RequestReqDto {
        private String email;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class ConfirmReqDto {
        private String email;
        private String code;
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ConfirmResDto {
        private String verificationToken;
    }
}