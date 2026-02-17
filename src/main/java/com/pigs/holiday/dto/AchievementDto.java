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

        public static ListResDto from(Achievement achievement) {
            return ListResDto.builder()
                    .id(achievement.getId())
                    .title(achievement.getTitle())
                    .imageUrl(achievement.getImageUrl())
                    .build();
        }
    }
}
