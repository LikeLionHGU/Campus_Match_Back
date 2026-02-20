package com.pigs.holiday.dto;

import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.Gallery;
import com.pigs.holiday.domain.GalleryImage;
import com.pigs.holiday.domain.MatchHistory;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

public class GalleryDto {

    // Create Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateReqDto {
        String title;
        LocalDate matchDate;

        public Gallery toEntity(Club club) { return Gallery.of(getMatchDate(), getTitle(), false, club); }

        public Gallery toFinish(Club club) { return Gallery.of(getMatchDate(), getTitle(), true, club); }

        public static CreateReqDto toCreateReqDto(MatchHistoryDto.FinishReqDto finishReqDto, MatchHistory matchHistory) {
            return builder()
                    .title(finishReqDto.getTitle())
                    .matchDate(matchHistory.getMatchDate())
                    .build();
        }
    }

    // Create Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateResDto {
        Long galleryId;

        public static CreateResDto toCreateResDto(Gallery gallery) {
            return builder().galleryId(gallery.getId()).build();
        }
    }

    // List Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ListReqDto {
        String keyword;
    }

    // List Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ListResDto {
        Long galleryId;
        String title;
        LocalDate matchDate;
        String imageUrl;
        Boolean isOfficial;
    }

    // Detail
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto {
        String title;
        LocalDate matchDate;
        Boolean isOfficial;
        Boolean isMine;
        List<String> imageUrls;

        public static DetailResDto toDetailResDto(Gallery gallery, Boolean isMine) {
            return builder()
                    .title(gallery.getTitle())
                    .matchDate(gallery.getMatchDate())
                    .isOfficial(gallery.getIsOfficial())
                    .isMine(isMine)
                    .imageUrls(gallery.getGalleryImageList().stream().map(GalleryImage::getImageUrl).toList())
                    .build();
        }
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto {
        String title;
        LocalDate matchDate;
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateResDto {
        Long galleryId;

        public static UpdateResDto toUpdateResDto(Gallery gallery) {
            return builder().galleryId(gallery.getId()).build();
        }
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DeleteResDto {
        Long galleryId;
        public static DeleteResDto toDeleteResDto(Gallery gallery) {
            return builder().galleryId(gallery.getId()).build();
        }
    }

    // Dashboard
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DashboardResDto {
        Long galleryId;
        String title;
        LocalDate matchDate;
        String imageUrl;

        public static DashboardResDto toDashboardResDto(Gallery gallery) {
            return builder()
                    .galleryId(gallery.getId())
                    .title(gallery.getTitle())
                    .matchDate(gallery.getMatchDate())
                    .imageUrl(gallery.getGalleryImageList().get(0).getImageUrl())
                    .build();
        }
    }
}
