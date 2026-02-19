package com.pigs.holiday.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.MatchPost;
import com.pigs.holiday.domain.MatchRequest;
import com.pigs.holiday.domain.Schedule;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ScheduleDto {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReqDto {
        String title;
        LocalDate startDate;
        LocalDate endDate;
        @JsonFormat(pattern = "HH:mm")
        LocalTime startTime;
        @JsonFormat(pattern = "HH:mm")
        LocalTime endTime;

        public Schedule toEntity(Club club) {
            return Schedule.of(getTitle(), getStartDate(), getEndDate(), club, getStartTime(), getEndTime());
        }
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResDto {
        Long scheduleId;

        public static ScheduleDto.CreateResDto toCreateResDto(Schedule schedule) {
            return builder()
                    .scheduleId(schedule.getId())
                    .build();
        }
    }

    // Calendar Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CalendarResDto {
        Boolean isMine;

        List<ScheduleDto.ScheduleResDto> scheduleResDtoList;
        List<MatchPostDto.ScheduleResDto> matchPostResDtoList;

        public static ScheduleDto.CalendarResDto toCalendarResDto(Boolean isMine) {
            return builder()
                    .isMine(isMine)
                    .build();
        }
    }

    // Schedule Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ScheduleResDto {
        Long scheduleId;
        String title;
        LocalDate startDate;
        LocalDate endDate;

        public static ScheduleDto.ScheduleResDto toScheduleResDto(Schedule schedule) {
            return builder()
                    .scheduleId(schedule.getId())
                    .title(schedule.getTitle())
                    .startDate(schedule.getStartDate())
                    .endDate(schedule.getEndDate())
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailResDto {
        Long scheduleId;
        String title;
        LocalDate startDate;
        LocalDate endDate;
        @JsonFormat(pattern = "HH:mm")
        LocalTime startTime;
        @JsonFormat(pattern = "HH:mm")
        LocalTime endTime;


        public static DetailResDto toDetailResDto(Schedule schedule) {
            return builder()
                    .scheduleId(schedule.getId())
                    .title(schedule.getTitle())
                    .startDate(schedule.getStartDate())
                    .endDate(schedule.getEndDate())
                    .startTime(schedule.getStartTime())
                    .endTime(schedule.getEndTime())
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateReqDto{
        String title;
        LocalDate startDate;
        LocalDate endDate;
        @JsonFormat(pattern = "HH:mm")
        LocalTime startTime;
        @JsonFormat(pattern = "HH:mm")
        LocalTime endTime;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateResDto {
        Long scheduleId;

        public static UpdateResDto toUpdateResDto(Schedule schedule) {
            return builder()
                    .scheduleId(schedule.getId())
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeleteResDto {
        Long scheduleId;

        public static DeleteResDto toDeleteResDto(Schedule schedule) {
            return builder()
                    .scheduleId(schedule.getId())
                    .build();
        }
    }

    // Dashboard
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DashboardResDto {
        Long scheduleId;
        String title;
        LocalDate startDate;
        LocalDate endDate;

        public static DashboardResDto toDashboardResDto(Schedule schedule) {
            return builder()
                    .scheduleId(schedule.getId())
                    .title(schedule.getTitle())
                    .startDate(schedule.getStartDate())
                    .endDate(schedule.getEndDate())
                    .build();
        }
    }
}
