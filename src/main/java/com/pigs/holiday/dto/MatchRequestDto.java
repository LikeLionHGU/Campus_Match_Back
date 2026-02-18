package com.pigs.holiday.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.MatchPost;
import com.pigs.holiday.domain.MatchRequest;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class MatchRequestDto {

    // Create Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateReqDto {
        @JsonFormat(pattern = "HH:mm")
        LocalTime startTime;
        @JsonFormat(pattern = "HH:mm")
        LocalTime endTime;

        public MatchRequest toEntity(MatchPost matchPost, Club Request) {
            return MatchRequest.of(startTime, endTime, matchPost, Request);
        }
    }

    // Create Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateResDto {
        Long matchRequestId;

        public static CreateResDto toCreateResDto(MatchRequest matchRequest) {
            return builder()
                    .matchRequestId(matchRequest.getId())
                    .build();
        }
    }

    // Dashboard List Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DashboardResDto {
        Long matchRequestId;
        LocalDate matchDate;
        String university;
        String clubName;

        public static DashboardResDto toDashboardReceiveResDto(MatchRequest matchRequest) {
            return builder()
                    .matchRequestId(matchRequest.getId())
                    .matchDate(matchRequest.getMatchPost().getMatchDate())
                    .university(matchRequest.getSenderClub().getUniversity())
                    .clubName(matchRequest.getSenderClub().getClubName())
                    .build();
        }

        public static DashboardResDto toDashboardSendResDto(MatchRequest matchRequest) {
            return builder()
                    .matchRequestId(matchRequest.getId())
                    .matchDate(matchRequest.getMatchPost().getMatchDate())
                    .university(matchRequest.getMatchPost().getHomeClub().getUniversity())
                    .clubName(matchRequest.getMatchPost().getHomeClub().getClubName())
                    .build();
        }
    }

    // List Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ListResDto {
        Long matchRequestId;
        LocalDate matchDate;
        String sportCategory;
        Long clubId;
        String imageUrl;
        String clubName;
        String university;
        String region;
        String location;
        double mannerScore;
        Boolean isMine;

        public static ListResDto toReceiveListResDto(MatchRequest matchRequest, Boolean isMine) {
            return builder()
                    .matchRequestId(matchRequest.getId())
                    .matchDate(matchRequest.getMatchPost().getMatchDate())
                    .sportCategory(matchRequest.getMatchPost().getSportCategory())
                    .clubId(matchRequest.getSenderClub().getId())
                    .imageUrl(matchRequest.getSenderClub().getImageUrl())
                    .clubName(matchRequest.getSenderClub().getName())
                    .university(matchRequest.getSenderClub().getUniversity())
                    .region(matchRequest.getSenderClub().getRegion())
                    .location(matchRequest.getMatchPost().getLocation())
                    .mannerScore(matchRequest.getSenderClub().getMannerScore())
                    .isMine(isMine)
                    .build();
        }

        public static ListResDto toSendListResDto(MatchRequest matchRequest, Boolean isMine) {
            return builder()
                    .matchRequestId(matchRequest.getId())
                    .matchDate(matchRequest.getMatchPost().getMatchDate())
                    .sportCategory(matchRequest.getMatchPost().getSportCategory())
                    .clubId(matchRequest.getMatchPost().getHomeClub().getId())
                    .imageUrl(matchRequest.getMatchPost().getHomeClub().getImageUrl())
                    .clubName(matchRequest.getMatchPost().getHomeClub().getName())
                    .university(matchRequest.getMatchPost().getHomeClub().getUniversity())
                    .region(matchRequest.getMatchPost().getHomeClub().getRegion())
                    .location(matchRequest.getMatchPost().getLocation())
                    .mannerScore(matchRequest.getMatchPost().getHomeClub().getMannerScore())
                    .isMine(isMine)
                    .build();
        }
    }

    // Detail Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto {
        LocalDate matchDate;
        @JsonFormat(pattern = "HH:mm")
        LocalTime startTime;
        @JsonFormat(pattern = "HH:mm")
        LocalTime endTime;
        String location;
        String phone;
        String content;

        public static DetailResDto toReceiveDetailResDto(MatchRequest matchRequest) {
            return builder()
                    .matchDate(matchRequest.getMatchPost().getMatchDate())
                    .startTime(matchRequest.getStartTime())
                    .endTime(matchRequest.getEndTime())
                    .location(matchRequest.getMatchPost().getLocation())
                    .phone(matchRequest.getSenderClub().getPhone())
                    .content(matchRequest.getMatchPost().getContent())
                    .build();
        }

        public static DetailResDto toSendDetailResDto(MatchRequest matchRequest) {
            return builder()
                    .matchDate(matchRequest.getMatchPost().getMatchDate())
                    .startTime(matchRequest.getStartTime())
                    .endTime(matchRequest.getEndTime())
                    .location(matchRequest.getMatchPost().getLocation())
                    .phone(matchRequest.getMatchPost().getHomeClub().getPhone())
                    .content(matchRequest.getMatchPost().getContent())
                    .build();
        }
    }

    // Delete Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DeleteResDto {
        Long matchRequestId;
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateResDto {
        Long matchRequestId;
    }


}