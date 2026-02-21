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

    // Signup
    @PostMapping(value = "/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ClubDto.SignupResDto> signup(@RequestPart("request") ClubDto.SignupReqDto signupReqDto, @RequestPart(value = "image", required = false) MultipartFile file)  throws IOException {
        String s3Url = null;
        if (file != null && !file.isEmpty()) {
            s3Url = fileService.uploadFile(file, "likepigs/");
        }

        return ResponseEntity.ok(clubService.signup(signupReqDto, s3Url));
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

    // Description
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/description/{clubId}")
    public ResponseEntity<ClubDto.DescriptionResDto> description(@PathVariable Long clubId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(clubService.description(clubId, getReqUserId(principalDetails)));
    }

    // Description Update
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/description")
    public ResponseEntity<ClubDto.DescriptionUpdateResDto> descriptionUpdate(@RequestBody ClubDto.DescriptionUpdateReqDto descriptionUpdateReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(clubService.descriptionUpdate(descriptionUpdateReqDto, getReqUserId(principalDetails)));
    }

    // Award
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/award")
    public ResponseEntity<ClubDto.AwardResDto> award(@RequestBody ClubDto.AwardReqDto awardReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(clubService.award(awardReqDto, getReqUserId(principalDetails)));
    }

    // Award
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/award/{awardId}")
    public ResponseEntity<ClubDto.AwardDeleteResDto> awardDelete(@PathVariable Long awardId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(clubService.awardDelete(awardId, getReqUserId(principalDetails)));
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

    // List
    @PreAuthorize("hasRole('USER')")
    @PostMapping("")
    public ResponseEntity<List<ClubDto.ListResDto>> list(@RequestBody ClubDto.ListReqDto listReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(clubService.list(listReqDto, getReqUserId(principalDetails)));
    }

    // Search
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/search")
    public ResponseEntity<List<ClubDto.SearchResDto>> search(@RequestBody ClubDto.SearchReqDto searchReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(clubService.search(searchReqDto, getReqUserId(principalDetails)));
    }
}
