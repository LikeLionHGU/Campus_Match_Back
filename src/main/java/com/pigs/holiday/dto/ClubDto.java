package com.pigs.holiday.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pigs.holiday.domain.Gallery;
import lombok.*;
import com.pigs.holiday.domain.Club;
import lombok.experimental.SuperBuilder;

import java.util.List;


public class ClubDto {

    // Signup Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class SignupReqDto {
        String username;
        String password;
        String name;
        String university;
        String phone;
        String email;
        String clubName;
        String description;
        String region;
        String sportCategory;

        public Club toEntity(String s3Url) { return Club.of(getUsername(), getPassword(), getName(), getUniversity(), getPhone(), getEmail(), getClubName(), getDescription(), getRegion(), getSportCategory(), s3Url, 0, 0, 0, 0, 36.5); }
    }

    // Signup Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class SignupResDto {
        Long id;
    }

    // Login Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class LoginReqDto {
        public String username;
        public String password;
    }

    // Info Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class InfoResDto {
        Long clubId;
        String clubName;
        String imageUrl;

        public static ClubDto.InfoResDto toInfoResDto(Club club) {
            return builder()
                    .clubId(club.getId())
                    .clubName(club.getName())
                    .imageUrl(club.getImageUrl())
                    .build();
        }
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DashboardResDto {
        Long clubId;
        String clubName;
        String description;
        int totalMatches;
        int totalWins;
        int totalDraws;
        int totalLosses;
        double mannerScore;
        Boolean isMine;

        List<MatchPostDto.DashboardResDto> upcomingResDtoList;
        List<MatchPostDto.DashboardResDto> ongoingResDtoList;
        List<MatchRequestDto.DashboardResDto> receiveResDtoList;
        List<MatchRequestDto.DashboardResDto> sendResDtoList;
        List<ScheduleDto.DashboardResDto> scheduleResDtoList;
        List<GalleryDto.DashboardResDto> galleryResDtoList;

        public static DashboardResDto toDashboardResDto(Club club) {
            return builder()
                    .clubId(club.getId())
                    .clubName(club.getName())
                    .description(club.getDescription())
                    .totalMatches(club.getTotalMatches())
                    .totalWins(club.getTotalWins())
                    .totalDraws(club.getTotalDraws())
                    .totalLosses(club.getTotalLosses())
                    .mannerScore(club.getMannerScore())
                    .build();
        }
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ListResDto {
        String clubName;
        String description;
        int totalMatches;
        int totalWins;
        int totalDraws;
        int totalLosses;
        int mannerScore;

        public static ListResDto toListResDto(Club club) {
            return ListResDto.builder()
                    .clubName(club.getClubName())
                    .description(club.getDescription())
                    .totalMatches(club.getTotalMatches())
                    .totalWins(club.getTotalWins())
                    .totalDraws(club.getTotalDraws())
                    .totalLosses(club.getTotalLosses())
                    .build();
        }
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DashboardUpdateReqDto {
        String description;
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DashboardUpdateResDto {
        Long clubId;
    }


    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class SettingDetailResDto {
        String clubName;
        String username;
        String password;
        String name;
        String university;
        String phone;
        String email;
        String imageUrl;

        public static SettingDetailResDto toSettingDetailResDto(Club club) {
            return SettingDetailResDto.builder()
                    .clubName(club.getClubName())
                    .username(club.getUsername())
                    .password(club.getPassword())
                    .name(club.getName())
                    .university(club.getUniversity())
                    .phone(club.getPhone())
                    .email(club.getEmail())
                    .imageUrl(club.getImageUrl())
                    .build();
        }
    }

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class SettingUpdateReqDto {
        String name;
        String username;
        String password;
        String university;
        String clubName;
        String phone;
        String email;
        String imageUrl;
    }

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class SettingUpdateResDto{
        Long clubId;
    }



    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class SettingDeleteResDto {
        Long clubId;
    }

    //동아리 온도 수정 미완

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class MannerScoreReq {
        Boolean manner;
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class MannerScoreRes {
        Long clubId;
    }
    //검색 페이지 미완
















}
