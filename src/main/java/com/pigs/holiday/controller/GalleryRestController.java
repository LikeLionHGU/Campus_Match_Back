package com.pigs.holiday.controller;

import com.pigs.holiday.dto.ClubDto;
import com.pigs.holiday.dto.GalleryDto;
import com.pigs.holiday.security.PrincipalDetails;
import com.pigs.holiday.service.ClubService;
import com.pigs.holiday.service.FileService;
import com.pigs.holiday.service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/gallery")
@RestController
public class GalleryRestController {

    public Long getReqUserId(PrincipalDetails principalDetails) {
        if(principalDetails == null || principalDetails.getUser() == null || principalDetails.getUser().getId() == null) {
            return null;
        }

        return principalDetails.getUser().getId();
    }

    final GalleryService galleryService;
    private final FileService fileService;

    // Create
    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<GalleryDto.CreateResDto> create(@RequestPart("request") GalleryDto.CreateReqDto createReqDto, @RequestPart("images") List<MultipartFile> files,  @AuthenticationPrincipal PrincipalDetails principalDetails) throws IOException{
        List<String> s3Urls = fileService.uploadFiles(files, "likepigs/");

        return ResponseEntity.ok(galleryService.create(createReqDto, s3Urls, getReqUserId(principalDetails)));
    }

    // List
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{clubId}")
    public ResponseEntity<List<GalleryDto.ListResDto>> list(@PathVariable Long clubId, @RequestBody GalleryDto.ListReqDto listReqDto) {
        return ResponseEntity.ok(galleryService.list(clubId, listReqDto));
    }

    // MatchList
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/match/{clubId}")
    public ResponseEntity<List<GalleryDto.ListResDto>> matchList(@PathVariable Long clubId, @RequestBody GalleryDto.ListReqDto listReqDto) {
        return ResponseEntity.ok(galleryService.matchList(clubId, listReqDto));
    }

    // MyClubList
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/myClub/{clubId}")
    public ResponseEntity<List<GalleryDto.ListResDto>> myClubList(@PathVariable Long clubId, @RequestBody GalleryDto.ListReqDto listReqDto) {
        return ResponseEntity.ok(galleryService.myClubList(clubId, listReqDto));
    }

    // Detail
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/detail/{galleryId}")
    public ResponseEntity<GalleryDto.DetailResDto> detail(@PathVariable Long galleryId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(galleryService.detail(galleryId, getReqUserId(principalDetails)));
    }

    // Update
    @PreAuthorize("hasRole('USER')")
    @PutMapping(value = "/{galleryId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<GalleryDto.UpdateResDto> update(@PathVariable Long galleryId, @RequestPart("request") GalleryDto.UpdateReqDto updateReqDto, @RequestPart(value = "images", required = false) List<MultipartFile> files, @AuthenticationPrincipal PrincipalDetails principalDetails) throws IOException{
        List<String> s3Urls = null;
        if (files != null && !files.isEmpty()) {
            s3Urls = fileService.uploadFiles(files, "likepigs/");
        }

        return ResponseEntity.ok(galleryService.update(galleryId, updateReqDto, s3Urls, getReqUserId(principalDetails)));
    }

    // Delete
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{galleryId}")
    public ResponseEntity<GalleryDto.DeleteResDto> delete(@PathVariable Long galleryId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(galleryService.delete(galleryId, getReqUserId(principalDetails)));
    }
}
