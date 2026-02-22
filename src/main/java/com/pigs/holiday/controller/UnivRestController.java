package com.pigs.holiday.controller;

import com.pigs.holiday.dto.UnivDto;
import com.pigs.holiday.service.UnivService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/univ")
@RestController
public class UnivRestController {

    private final UnivService univService;

    // Search
    @PostMapping("")
    public ResponseEntity<List<UnivDto.SearchResDto>> search(@RequestBody UnivDto.SearchReqDto searchReqDto) {
        return ResponseEntity.ok(univService.search(searchReqDto));
    }

}
