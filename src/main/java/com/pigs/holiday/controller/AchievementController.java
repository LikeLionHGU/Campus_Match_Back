package com.pigs.holiday.controller;

import com.pigs.holiday.dto.AchievementDto;
import com.pigs.holiday.service.AchievementService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/api/badge")
@RestController
public class AchievementController {

    final AchievementService achievementService;

    @GetMapping("/{clubId}")
    public ResponseEntity<List<AchievementDto.ListResDto>> list(@PathVariable Long clubId) {
        return ResponseEntity.ok(achievementService.checkAndAssignAchievements(clubId));
    }
}
