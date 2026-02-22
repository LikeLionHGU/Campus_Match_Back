package com.pigs.holiday.dto;

import lombok.*;


public class UnivDto {

    // Search Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class SearchReqDto {
        String keyword;
    }

    // Search Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class SearchResDto {
        Long univId;
        String name;
        String address;
    }
}
