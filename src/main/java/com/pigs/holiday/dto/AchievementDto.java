package com.pigs.holiday.dto;

import lombok.*;


public class AchievementDto {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListResDto {
        private Long id;
        private String title;
        private String imageUrl;
        private boolean isAcquired;
    }
}
