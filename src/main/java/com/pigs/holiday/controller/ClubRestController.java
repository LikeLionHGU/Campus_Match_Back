package com.pigs.holiday.controller;

import com.pigs.holiday.security.PrincipalDetails;
import com.pigs.holiday.service.FileService;
import lombok.RequiredArgsConstructor;
import com.pigs.holiday.dto.ClubDto;
import com.pigs.holiday.service.ClubService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/club")
@RestController
public class ClubRestController {

    public Long getReqUserId(PrincipalDetails principalDetails) {
        if(principalDetails == null || principalDetails.getUser() == null || principalDetails.getUser().getId() == null) {
            return null;
        }

        return principalDetails.getUser().getId();
    }

    final ClubService clubService;
    final FileService fileService;

    @PostMapping(value = "/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ClubDto.SignupResDto> signup(@RequestPart("request") ClubDto.SignupReqDto signupReqDto, @RequestPart(value = "image", required = false) MultipartFile file)  throws IOException {
        String s3Url = null;
        if (file != null && !file.isEmpty()) {
            s3Url = fileService.uploadFile(file, "likepigs/");
        }

        return ResponseEntity.ok(clubService.signup(signupReqDto, s3Url));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/clubId")
    public ResponseEntity<Long> sendId(@AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(getReqUserId(principalDetails));
    }

    // Info
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/info")
    public ResponseEntity<ClubDto.InfoResDto> info(@AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(clubService.info(getReqUserId(principalDetails)));
    }

    // Dashboard
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/dashboard/{clubId}")
    public ResponseEntity<ClubDto.DashboardResDto> dashboard(@PathVariable Long clubId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(clubService.dashboard(clubId, getReqUserId(principalDetails)));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("")
    public ResponseEntity<List<ClubDto.ListResDto>> list() {
        return ResponseEntity.ok(clubService.list());
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/dashboard/{clubId}")
    public ResponseEntity<ClubDto.DashboardUpdateResDto> dashboardUpdate(@RequestBody ClubDto.DashboardUpdateReqDto dashboardUpdateReqDto, @PathVariable Long clubId) {
        return ResponseEntity.ok(clubService.dashboardUpdate(dashboardUpdateReqDto, clubId));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/setting/{clubId}")
    public ResponseEntity<ClubDto.SettingDetailResDto> settingDetail(@PathVariable Long clubId) {
        return ResponseEntity.ok(clubService.settingDetail(clubId));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping(value = "/setting/{clubId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ClubDto.SettingUpdateResDto> settingUpdate(@PathVariable Long clubId, @RequestPart("request") ClubDto.SettingUpdateReqDto settingUpdateReqDto, @RequestPart(value = "image", required = false) MultipartFile file) throws IOException {
        String s3Url = null;
        if (file != null && !file.isEmpty()) {
            s3Url = fileService.uploadFile(file, "likepigs/");
        }

        return ResponseEntity.ok(clubService.settingUpdate(settingUpdateReqDto, clubId, s3Url));
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/setting/{clubId}")
    public ResponseEntity<ClubDto.SettingDeleteResDto> delete(@PathVariable Long clubId) {
        return ResponseEntity.ok(clubService.delete(clubId));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/mannerScore/{clubId}")
    public ResponseEntity<ClubDto.MannerScoreRes> manner(@RequestBody ClubDto.MannerScoreReq mannerScoreReq, @PathVariable Long clubId) {
        return ResponseEntity.ok(clubService.manner(mannerScoreReq,clubId));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("")
    public ResponseEntity<List<ClubDto.SearchRes>> searchList(@RequestParam(required = false)String region, @RequestParam(required = false)String sportCategory) {
        return ResponseEntity.ok(clubService.searchList(region, sportCategory));
    }

}
