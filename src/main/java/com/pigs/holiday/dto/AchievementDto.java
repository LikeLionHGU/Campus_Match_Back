package com.pigs.holiday.dto;

import com.pigs.holiday.domain.Achievement;

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
        Boolean isAcquired;
    }
}
