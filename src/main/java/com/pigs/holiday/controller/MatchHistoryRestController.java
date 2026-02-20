package com.pigs.holiday.controller;

import com.pigs.holiday.dto.GalleryDto;
import com.pigs.holiday.dto.MatchHistoryDto;
import com.pigs.holiday.security.PrincipalDetails;
import com.pigs.holiday.service.FileService;
import com.pigs.holiday.service.MatchHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/matchHistory")
@RestController
public class MatchHistoryRestController {

    public Long getReqUserId(PrincipalDetails principalDetails) {
        if(principalDetails == null || principalDetails.getUser() == null || principalDetails.getUser().getId() == null) {
            return null;
        }

        return principalDetails.getUser().getId();
    }

    final MatchHistoryService matchHistoryService;
    final FileService fileService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("")
    public ResponseEntity<MatchHistoryDto.CreateResDto> create(@RequestBody MatchHistoryDto.CreateReqDto createReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(matchHistoryService.create(createReqDto, getReqUserId(principalDetails)));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{clubId}")
    public ResponseEntity<List<MatchHistoryDto.ListResDto>> list(@PathVariable Long clubId) {
        return ResponseEntity.ok(matchHistoryService.list(clubId));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<MatchHistoryDto.UpdateResDto> update(@RequestBody MatchHistoryDto.UpdateReqDto updateReqDto,  @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(matchHistoryService.update(updateReqDto, principalDetails.getClub().getId()));
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{matchHistoryId}")
    public ResponseEntity<MatchHistoryDto.DeleteResDto> delete(@PathVariable Long matchHistoryId, @AuthenticationPrincipal PrincipalDetails principalDetails ){
        return ResponseEntity.ok(matchHistoryService.delete(matchHistoryId, principalDetails.getClub().getId()));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/finish/{matchPostId}")
    public ResponseEntity<MatchHistoryDto.FinishResDto> finish(@PathVariable Long matchPostId, @RequestPart("request") MatchHistoryDto.FinishReqDto finishReqDto, @RequestPart("images") List<MultipartFile> files, @AuthenticationPrincipal PrincipalDetails principalDetails) throws IOException {
        List<String> s3Urls = fileService.uploadFiles(files, "likepigs/");

        return ResponseEntity.ok(matchHistoryService.finish(matchPostId, finishReqDto, s3Urls, getReqUserId(principalDetails)));
    }

}
