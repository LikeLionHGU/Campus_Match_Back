package com.pigs.holiday.dto;

import com.pigs.holiday.domain.Award;
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

    // Dashboard Response Dto
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
        List<MatchPostDto.DashboardResDto> matchResDtoList;
        List<MatchPostDto.DashboardResDto> pastResDtoList;
        List<MatchRequestDto.DashboardResDto> receiveResDtoList;
        List<MatchRequestDto.DashboardResDto> sendResDtoList;
        List<ScheduleDto.DashboardResDto> scheduleResDtoList;
        List<GalleryDto.DashboardResDto> galleryResDtoList;

        public static DashboardResDto toDashboardResDto(Club club) {
            return builder()
                    .clubId(club.getId())
                    .clubName(club.getClubName())
                    .description(club.getDescription())
                    .totalMatches(club.getTotalMatches())
                    .totalWins(club.getTotalWins())
                    .totalDraws(club.getTotalDraws())
                    .totalLosses(club.getTotalLosses())
                    .mannerScore(club.getMannerScore())
                    .build();
        }
    }

    // Description Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DescriptionResDto {
        double mannerScore;
        String description;
        List<AwardResDto> awardResDtoList;
        Boolean isMine;

        public static DescriptionResDto toDescriptionResDto(Club club, List<AwardResDto> awardResDtoList, Boolean isMine) {
            return builder()
                    .mannerScore(club.getMannerScore())
                    .description(club.getDescription())
                    .awardResDtoList(awardResDtoList)
                    .isMine(isMine)
                    .build();
        }
    }

    // Award Create Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class AwardReqDto {
        String title;

        public Award toEntity(Club club) { return Award.of(title, club);}
    }

    // Award Create Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class AwardResDto {
        Long awardId;
        String title;

        public static AwardResDto toAwardResDto(Award award) {
            return builder()
                    .awardId(award.getId())
                    .title(award.getTitle())
                    .build();
        }
    }

    // AwardDelete Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class AwardDeleteResDto {
        Long awardId;

        public static AwardDeleteResDto toAwardDeleteResDto(Award award) {
            return builder()
                    .awardId(award.getId())
                    .build();
        }
    }

    // Description update Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DescriptionUpdateReqDto {
        String description;
    }

    // Description update Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DescriptionUpdateResDto {
        Long clubId;

        public static DescriptionUpdateResDto toDescriptionUpdateResDto(Club club) {
            return builder()
                    .clubId(club.getId())
                    .build();
        }
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

    // CLub List
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ListReqDto {
        List<String> sportCategoryList;
        List<String> regionList;
        String keyword;
    }

    // CLub List
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ListResDto {
        Long clubId;
        String region;
        String university;
        String clubName;
        String sportCategory;
        double mannerScore;
    }

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class SearchReqDto {
        String keyword;
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class SearchResDto {
        Long clubId;
        String clubName;
        String university;
    }
}
