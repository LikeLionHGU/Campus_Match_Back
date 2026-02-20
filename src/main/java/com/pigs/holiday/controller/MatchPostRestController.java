package com.pigs.holiday.controller;

import com.pigs.holiday.dto.MatchPostDto;
import com.pigs.holiday.security.PrincipalDetails;
import com.pigs.holiday.service.MatchPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/matchPost")
@RestController
public class MatchPostRestController {

    final MatchPostService matchPostService;

    public Long getReqUserId(PrincipalDetails principalDetails) {
        if(principalDetails == null || principalDetails.getUser() == null || principalDetails.getUser().getId() == null) {
            return null;
        }

        return principalDetails.getUser().getId();
    }

    // Create
    @PreAuthorize("hasRole('USER')")
    @PostMapping("")
    public ResponseEntity<MatchPostDto.CreateResDto> create(@RequestBody MatchPostDto.CreateReqDto createReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(matchPostService.create(createReqDto, getReqUserId(principalDetails)));
    }

    // List
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/list")
    public ResponseEntity<List<MatchPostDto.ListResDto>> list(@RequestBody MatchPostDto.ListReqDto listReqDto){
        return ResponseEntity.ok(matchPostService.list(listReqDto));
    }

    // MineList
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/mine")
    public ResponseEntity<List<MatchPostDto.ListResDto>> mineList(@RequestBody MatchPostDto.ListReqDto listReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(matchPostService.mineList(listReqDto, getReqUserId(principalDetails)));
    }

    // OtherList
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/other")
    public ResponseEntity<List<MatchPostDto.ListResDto>> otherList(@RequestBody MatchPostDto.ListReqDto listReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(matchPostService.otherList(listReqDto, getReqUserId(principalDetails)));
    }

    // Detail
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{matchPostId}")
    public ResponseEntity<MatchPostDto.DetailResDto> detail(@PathVariable Long matchPostId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(matchPostService.detail(matchPostId, getReqUserId(principalDetails)));
    }

    // Update
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{matchPostId}")
    public ResponseEntity<MatchPostDto.UpdateResDto> update(@PathVariable Long matchPostId, @RequestBody MatchPostDto.UpdateReqDto updateReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(matchPostService.update(matchPostId, updateReqDto, getReqUserId(principalDetails)));
    }

    // Delete
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{matchPostId}")
    public ResponseEntity<MatchPostDto.DeleteResDto> delete(@PathVariable Long matchPostId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(matchPostService.delete(matchPostId, getReqUserId(principalDetails)));
    }

    // UpcomingList
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/upcoming")
    public ResponseEntity<List<MatchPostDto.ListResDto>> upcomingList(@AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(matchPostService.upcomingList(getReqUserId(principalDetails)));
    }

    // UpcomingDetail
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/upcoming/{matchPostId}")
    public ResponseEntity<MatchPostDto.IngDetailResDto> upcomingDetail(@PathVariable Long matchPostId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(matchPostService.upcomingDetail(matchPostId, getReqUserId(principalDetails)));
    }

    // UpcomingDelete
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/upcoming/{matchPostId}")
    public ResponseEntity<MatchPostDto.DeleteResDto> upcomingDelete(@PathVariable Long matchPostId, @RequestBody MatchPostDto.UpcomingDeleteReqDto upcomingDeleteReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(matchPostService.upcomingDelete(matchPostId, upcomingDeleteReqDto, getReqUserId(principalDetails)));
    }

    // OngoingList
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/ongoing")
    public ResponseEntity<List<MatchPostDto.ListResDto>> ongoingList(@AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(matchPostService.ongoingList(getReqUserId(principalDetails)));
    }

    // OngoingDetail
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/ongoing/{matchPostId}")
    public ResponseEntity<MatchPostDto.IngDetailResDto> ongoingDetail(@PathVariable Long matchPostId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(matchPostService.ongoingDetail(matchPostId, getReqUserId(principalDetails)));
    }

    // OngoingDelete
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/ongoing/{matchPostId}")
    public ResponseEntity<MatchPostDto.DeleteResDto> ongoingDelete(@PathVariable Long matchPostId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(matchPostService.ongoingDelete(matchPostId, getReqUserId(principalDetails)));
    }

    // FinishList
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/finish")
    public ResponseEntity<List<MatchPostDto.ListResDto>> finishList(@AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(matchPostService.finishList(getReqUserId(principalDetails)));
    }

    // FinishDetail
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/finish/{matchPostId}")
    public ResponseEntity<MatchPostDto.FinishDetailResDto> finishDetail(@PathVariable Long matchPostId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(matchPostService.finishDetail(matchPostId, getReqUserId(principalDetails)));
    }

    // ScheduleDetail
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/schedule/detail/{clubId}")
    public ResponseEntity<MatchPostDto.ScheduleDetailResDto> scheduleDetail(@PathVariable Long clubId, @RequestParam("matchPostId") Long matchPostId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(matchPostService.scheduleDetail(clubId, matchPostId, getReqUserId(principalDetails)));
    }

}
