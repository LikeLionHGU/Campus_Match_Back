package com.pigs.holiday.dto;
import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.MatchHistory;
import com.pigs.holiday.domain.MatchPost;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


public class MatchHistoryDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReqDto {
        LocalDate matchDate;
        String location;
        Long awayClubId;
        Boolean matchType;
        String result;

        public MatchHistory toEntity(Club homeClub, Club awayClub) {
            return MatchHistory.of(getMatchDate(), getLocation(), getMatchType(), getResult(), false, homeClub, awayClub);
        }
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResDto {
        Long matchHistoryId;

        public static MatchHistoryDto.CreateResDto toCreateResDto(MatchHistory matchHistory) {
            return builder().matchHistoryId(matchHistory.getId()).build();
        }
    }

    // History Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class HistoryResDto {
        Boolean isMine;

        List<ListResDto> matchHistoryList;

        public static MatchHistoryDto.HistoryResDto toHistoryResDto(Boolean isMine) {
            return builder().isMine(isMine).build();
        }
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListReqDto{
        String keyword;
    }

    // List Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ListResDto {
        Long matchHistoryId;
        LocalDate matchDate;
        Long awayClubId;
        String imageUrl;
        String clubName;
        String university;
        String location;
        Boolean matchType;
        String result;
    }

    // Detail Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto {
        LocalDate matchDate;
        String location;
        Long awayClubId;
        String awayClubName;
        Boolean matchType;
        String result;

        public static DetailResDto toDetailResDto(MatchHistory matchHistory) {
            return builder()
                    .matchDate(matchHistory.getMatchDate())
                    .location(matchHistory.getLocation())
                    .awayClubId(matchHistory.getAwayClub().getId())
                    .awayClubName(matchHistory.getAwayClub().getClubName())
                    .matchType(matchHistory.getMatchType())
                    .result(matchHistory.getResult())
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateReqDto {
        LocalDate matchDate;
        String location;
        Long awayClubId;
        Boolean matchType;
        String result;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateResDto {
        Long matchHistoryId;

        public static UpdateResDto toUpdateResDto(MatchHistory matchHistory) {
            return builder().matchHistoryId(matchHistory.getId()).build();
        }
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeleteResDto {
        Long matchHistoryId;

        public static MatchHistoryDto.DeleteResDto toDeleteResDto(MatchHistory matchHistory) {
            return builder()
                    .matchHistoryId(matchHistory.getId())
                    .build();
        }
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class FinishReqDto {
        String title;
        Boolean matchType;
        String result;
        Boolean mannerScore;
        Boolean rematch;

        public MatchHistory toEntity(Club homeClub, Club awayClub, MatchPost matchPost){
            return MatchHistory.of(matchPost.getMatchDate(), matchPost.getLocation(), getMatchType(), getResult(), true, homeClub, awayClub);
        }
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class FinishResDto {
        Long matchHistoryId;

        public static MatchHistoryDto.FinishResDto toFinishResDto(MatchHistory matchHistory) {
            return builder().matchHistoryId(matchHistory.getId()).build();
        }
    }
}
