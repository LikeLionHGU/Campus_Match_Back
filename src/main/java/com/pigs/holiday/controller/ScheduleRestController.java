package com.pigs.holiday.controller;

import com.pigs.holiday.dto.ScheduleDto;
import com.pigs.holiday.security.PrincipalDetails;
import com.pigs.holiday.service.ClubService;
import com.pigs.holiday.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/schedule")
@RestController
public class ScheduleRestController {

    public Long getReqUserId(PrincipalDetails principalDetails) {
        if(principalDetails == null || principalDetails.getUser() == null || principalDetails.getUser().getId() == null) {
            return null;
        }

        return principalDetails.getUser().getId();
    }

    final ScheduleService scheduleService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("")
    public ResponseEntity<ScheduleDto.CreateResDto> create(@RequestBody ScheduleDto.CreateReqDto createReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(scheduleService.create(createReqDto, getReqUserId(principalDetails)));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{clubId}")
    public ResponseEntity<ScheduleDto.CalendarResDto> list(@PathVariable Long clubId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(scheduleService.list(clubId, getReqUserId(principalDetails)));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/detail/{scheduleId}")
    public ResponseEntity<ScheduleDto.DetailResDto> detail(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(scheduleService.detail(scheduleId));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleDto.UpdateResDto> update(@RequestBody ScheduleDto.UpdateReqDto updateReqDto, @PathVariable Long scheduleId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(scheduleService.update(updateReqDto, scheduleId, principalDetails.getClub().getId()));
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<ScheduleDto.DeleteResDto> delete(@PathVariable Long scheduleId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(scheduleService.delete(scheduleId, getReqUserId(principalDetails)));
    }
}
