package com.pigs.holiday.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.MatchPost;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MatchPostDto {

    // Create Request Dto
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReqDto {
        String sportCategory;
        LocalDate matchDate;
        String location;
        String locationDetail;
        @JsonFormat(pattern = "HH:mm")
        LocalTime startTime;
        @JsonFormat(pattern = "HH:mm")
        LocalTime endTime;
        String content;

        public MatchPost toEntity(Club requestClub) {
            return MatchPost.of(getSportCategory(), getMatchDate(), getLocation(), getLocationDetail(), getStartTime(), getEndTime(), getContent(), false, requestClub, null, null);
        }
    }

    // Create Response Dto
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResDto {
        Long matchPostId;

        public static CreateResDto toCreateResDto(MatchPost matchPost) {
            return builder()
                    .matchPostId(matchPost.getId())
                    .build();
        }
    }

    // List Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ListReqDto {
        List<String> sportCategoryList;
        List<String> regionList;
        LocalDate startDate;
        LocalDate endDate;
        String keyword;
    }

    // List Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ListResDto {
        Long matchPostId;
        LocalDate matchDate;
        String sportCategory;
        Long clubId;
        String imageUrl;
        String clubName;
        String university;
        String region;
        String location;
        double mannerScore;

        public static ListResDto toHomeListResDto(MatchPost matchPost) {
            return builder()
                    .matchPostId(matchPost.getId())
                    .matchDate(matchPost.getMatchDate())
                    .sportCategory(matchPost.getSportCategory())
                    .clubId(matchPost.getAwayClub().getId())
                    .imageUrl(matchPost.getAwayClub().getImageUrl())
                    .clubName(matchPost.getAwayClub().getClubName())
                    .university(matchPost.getAwayClub().getUniversity())
                    .region(matchPost.getAwayClub().getRegion())
                    .location(matchPost.getLocation())
                    .mannerScore(matchPost.getAwayClub().getMannerScore())
                    .build();
        }

        public static ListResDto toAwayListResDto(MatchPost matchPost) {
            return builder()
                    .matchPostId(matchPost.getId())
                    .matchDate(matchPost.getMatchDate())
                    .sportCategory(matchPost.getSportCategory())
                    .clubId(matchPost.getHomeClub().getId())
                    .imageUrl(matchPost.getHomeClub().getImageUrl())
                    .clubName(matchPost.getHomeClub().getClubName())
                    .university(matchPost.getHomeClub().getUniversity())
                    .region(matchPost.getHomeClub().getRegion())
                    .location(matchPost.getLocation())
                    .mannerScore(matchPost.getHomeClub().getMannerScore())
                    .build();
        }
    }

    // Detail Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto {
        String sportCategory;
        LocalDate matchDate;
        String location;
        String locationDetail;
        @JsonFormat(pattern = "HH:mm")
        LocalTime startTime;
        @JsonFormat(pattern = "HH:mm")
        LocalTime endTime;
        String phone;
        String content;
        Boolean isMine;

        public static DetailResDto toDetailResDto(MatchPost matchPost, Boolean isMine) {
            return builder()
                    .sportCategory(matchPost.getSportCategory())
                    .matchDate(matchPost.getMatchDate())
                    .location(matchPost.getLocation())
                    .locationDetail(matchPost.getLocationDetail())
                    .startTime(matchPost.getStartTime())
                    .endTime(matchPost.getEndTime())
                    .phone(matchPost.getHomeClub().getPhone())
                    .content(matchPost.getContent())
                    .isMine(isMine)
                    .build();
        }
    }

    // Update Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto {
        String sportCategory;
        LocalDate matchDate;
        String location;
        String locationDetail;
        @JsonFormat(pattern = "HH:mm")
        LocalTime startTime;
        @JsonFormat(pattern = "HH:mm")
        LocalTime endTime;
        String content;
    }

    // Update Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateResDto {
        Long matchPostId;

        public static UpdateResDto toUpdateResDto(MatchPost matchPost) {
            return UpdateResDto.builder().matchPostId(matchPost.getId()).build();
        }
    }

    // Delete Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DeleteResDto {
        Long matchPostId;
    }

    // Dashboard Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DashboardResDto {
        Long matchPostId;
        LocalDate matchDate;
        String university;
        String clubName;

        public static DashboardResDto toDashboardHomeResDto(MatchPost matchPost) {
            return builder()
                    .matchPostId(matchPost.getId())
                    .matchDate(matchPost.getMatchDate())
                    .university(matchPost.getAwayClub().getUniversity())
                    .clubName(matchPost.getAwayClub().getClubName())
                    .build();
        }

        public static DashboardResDto toDashboardAwayResDto(MatchPost matchPost) {
            return builder()
                    .matchPostId(matchPost.getId())
                    .matchDate(matchPost.getMatchDate())
                    .university(matchPost.getHomeClub().getUniversity())
                    .clubName(matchPost.getHomeClub().getClubName())
                    .build();
        }
    }

    // IngDetail Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class IngDetailResDto {
        @JsonFormat(pattern = "HH:mm")
        LocalTime startTime;
        @JsonFormat(pattern = "HH:mm")
        LocalTime endTime;
        String location;
        String locationDetail;
        String phone;
        String content;

        public static IngDetailResDto toIngHomeDetailResDto(MatchPost matchPost) {
            return builder()
                    .startTime(matchPost.getStartTime())
                    .endTime(matchPost.getEndTime())
                    .location(matchPost.getLocation())
                    .locationDetail(matchPost.getLocationDetail())
                    .phone(matchPost.getAwayClub().getPhone())
                    .content(matchPost.getContent())
                    .build();
        }

        public static IngDetailResDto toIngAwayDetailResDto(MatchPost matchPost) {
            return builder()
                    .startTime(matchPost.getStartTime())
                    .endTime(matchPost.getEndTime())
                    .location(matchPost.getLocation())
                    .locationDetail(matchPost.getLocationDetail())
                    .phone(matchPost.getHomeClub().getPhone())
                    .content(matchPost.getContent())
                    .build();
        }
    }

    // UpcomingDelete Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class UpcomingDeleteReqDto {
        String content;
    }

    // FinishDetail Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class FinishDetailResDto {
        Long clubId;
        LocalDate matchDate;
        @JsonFormat(pattern = "HH:mm")
        LocalTime startTime;
        @JsonFormat(pattern = "HH:mm")
        LocalTime endTime;
        Long oppositionClubId;
        String phone;
        String location;
        String locationDetail;
        String content;

        public static FinishDetailResDto toFinishHomeDetailResDto(MatchPost matchPost) {
            return builder()
                    .clubId(matchPost.getHomeClub().getId())
                    .matchDate(matchPost.getMatchDate())
                    .startTime(matchPost.getStartTime())
                    .endTime(matchPost.getEndTime())
                    .oppositionClubId(matchPost.getAwayClub().getId())
                    .phone(matchPost.getAwayClub().getPhone())
                    .location(matchPost.getLocation())
                    .locationDetail(matchPost.getLocationDetail())
                    .content(matchPost.getContent())
                    .build();
        }

        public static FinishDetailResDto toFinishAwayDetailResDto(MatchPost matchPost) {
            return builder()
                    .clubId(matchPost.getAwayClub().getId())
                    .matchDate(matchPost.getMatchDate())
                    .startTime(matchPost.getStartTime())
                    .endTime(matchPost.getEndTime())
                    .oppositionClubId(matchPost.getHomeClub().getId())
                    .phone(matchPost.getHomeClub().getPhone())
                    .location(matchPost.getLocation())
                    .locationDetail(matchPost.getLocationDetail())
                    .content(matchPost.getContent())
                    .build();
        }
    }

    // Schedule Detail Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ScheduleDetailResDto {
        Long matchPostId;
        String sportCategory;
        LocalDate matchDate;
        LocalTime startTime;
        LocalTime endTime;
        String location;
        String locationDetail;
        String university;
        String clubName;
        String phone;
        String content;
        Boolean status;

        public static ScheduleDetailResDto toScheduleHomeDetailDto(MatchPost matchPost) {
            return builder()
                    .matchPostId(matchPost.getId())
                    .sportCategory(matchPost.getSportCategory())
                    .matchDate(matchPost.getMatchDate())
                    .startTime(matchPost.getStartTime())
                    .endTime(matchPost.getEndTime())
                    .location(matchPost.getLocation())
                    .locationDetail(matchPost.getLocationDetail())
                    .university(matchPost.getAwayClub().getUniversity())
                    .clubName(matchPost.getAwayClub().getClubName())
                    .phone(matchPost.getAwayClub().getPhone())
                    .content(matchPost.getContent())
                    .status(matchPost.getStatus())
                    .build();
        }

        public static ScheduleDetailResDto toScheduleAwayDetailDto(MatchPost matchPost) {
            return builder()
                    .matchPostId(matchPost.getId())
                    .sportCategory(matchPost.getSportCategory())
                    .matchDate(matchPost.getMatchDate())
                    .startTime(matchPost.getStartTime())
                    .endTime(matchPost.getEndTime())
                    .location(matchPost.getLocation())
                    .locationDetail(matchPost.getLocationDetail())
                    .university(matchPost.getHomeClub().getUniversity())
                    .clubName(matchPost.getHomeClub().getClubName())
                    .phone(matchPost.getHomeClub().getPhone())
                    .content(matchPost.getContent())
                    .status(matchPost.getStatus())
                    .build();
        }
    }
}
