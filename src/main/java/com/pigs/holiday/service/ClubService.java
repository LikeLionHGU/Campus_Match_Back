package com.pigs.holiday.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import com.pigs.holiday.domain.Club;
import com.pigs.holiday.dto.ClubDto;
import com.pigs.holiday.repository.ClubRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClubService {

    final ClubRepository clubRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final MatchPostService matchPostService;
    private final MatchRequestService matchRequestService;
    private final ScheduleService scheduleService;
    private final GalleryService galleryService;

    // Signup
    public ClubDto.SignupResDto signup(ClubDto.SignupReqDto signupReqDto, String s3Url) {

        Club club = clubRepository.findByUsername(signupReqDto.getUsername()).orElse(null);
        if(club != null) {
            throw new RuntimeException("Already exist");
        }

        signupReqDto.setPassword(bCryptPasswordEncoder.encode(signupReqDto.getPassword()));
        club = clubRepository.save(signupReqDto.toEntity(s3Url));

        return club.toSignupResDto();
    }

    // Info
    @Transactional(readOnly = true)
    public ClubDto.InfoResDto info(Long requestClubId) {
        Club club = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("Club Info Error"));

        return ClubDto.InfoResDto.toInfoResDto(club);
    }


    @Transactional(readOnly = true)
    public ClubDto.DashboardResDto dashboard(Long clubId, Long requestClubId) {
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("Club Dashboard Error"));

        ClubDto.DashboardResDto dashboardResDto = ClubDto.DashboardResDto.toDashboardResDto(club);
        dashboardResDto.setIsMine(clubId.equals(requestClubId));
        dashboardResDto.setUpcomingResDtoList(matchPostService.upcomingDashboard(clubId));
        dashboardResDto.setOngoingResDtoList(matchPostService.ongoingDashboard(clubId));
        dashboardResDto.setReceiveResDtoList(matchRequestService.receiveDashboard(clubId));
        dashboardResDto.setSendResDtoList(matchRequestService.sendDashboard(clubId));
        dashboardResDto.setScheduleResDtoList(scheduleService.scheduleDashboard(clubId));
        dashboardResDto.setGalleryResDtoList(galleryService.galleryDashboard(clubId));

        return dashboardResDto;
    }

    @Transactional(readOnly = true)
    public List<ClubDto.ListResDto> list() {
        List<Club> clubList = clubRepository.findByDeleted(false).orElseThrow(() -> new EntityNotFoundException("clubDetail Error"));
        return clubList.stream().map(ClubDto.ListResDto :: toListResDto).toList();
    }

    //save를
    @Transactional
    public ClubDto.DashboardUpdateResDto dashboardUpdate(ClubDto.DashboardUpdateReqDto dashboardUpdate, Long clubId) {
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("clubUpdate Error"));
        club.setDescription(dashboardUpdate.getDescription());
        return ClubDto.DashboardUpdateResDto.builder().clubId(club.getId()).build();
    }


    @Transactional(readOnly = true)
    public ClubDto.SettingDetailResDto settingDetail(Long clubId) {
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("clubDetail Error"));
        return ClubDto.SettingDetailResDto.toSettingDetailResDto(club);
    }

    @Transactional
    public ClubDto.SettingUpdateResDto settingUpdate(ClubDto.SettingUpdateReqDto settingUpdateReqDto, Long clubId, String s3Url) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new EntityNotFoundException("Setting update Error: " + clubId + " not found"));

        if(settingUpdateReqDto.getUsername() != null && !settingUpdateReqDto.getUsername().isBlank()) {
            club.setUsername(settingUpdateReqDto.getUsername());
        }
        if(settingUpdateReqDto.getPassword() != null && !settingUpdateReqDto.getPassword().isBlank()) {
            club.setPassword(bCryptPasswordEncoder.encode(settingUpdateReqDto.getPassword()));
        }
        if(settingUpdateReqDto.getName() != null && !settingUpdateReqDto.getName().isBlank()) {
            club.setName(settingUpdateReqDto.getName());
        }
        if(settingUpdateReqDto.getUniversity() != null && !settingUpdateReqDto.getUniversity().isBlank()) {
            club.setUniversity(settingUpdateReqDto.getUniversity());
        }
        if(settingUpdateReqDto.getPhone() != null && !settingUpdateReqDto.getPhone().isBlank()) {
            club.setPhone(settingUpdateReqDto.getPhone());
        }
        if(settingUpdateReqDto.getEmail() != null && !settingUpdateReqDto.getEmail().isBlank()) {
            club.setEmail(settingUpdateReqDto.getEmail());
        }
        if(settingUpdateReqDto.getClubName() != null && !settingUpdateReqDto.getClubName().isBlank()) {
            club.setClubName(settingUpdateReqDto.getClubName());
        }
        if(s3Url != null && !s3Url.isBlank()){
            club.setImageUrl(s3Url);
        }

        return ClubDto.SettingUpdateResDto.builder().clubId(club.getId()).build();
    }


    @Transactional
    public ClubDto.SettingDeleteResDto delete(Long clubId) {
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("delete Error"));

        club.setDeleted(true);
        return ClubDto.SettingDeleteResDto.builder()
                .clubId(clubId)
                .build();
    }


    //매너온도 세팅하는 거 로직 짜야함

    @Transactional
    public ClubDto.MannerScoreRes manner(ClubDto.MannerScoreReq mannerScoreReq,Long clubId) {
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("manner Error"));

        if(mannerScoreReq.getManner() == true) {
            club.setMannerScore(club.getMannerScore() + 1);
        }
        else {
            club.setMannerScore(club.getMannerScore() - 1);
        }
        return ClubDto.MannerScoreRes.builder().clubId(clubId).build();
    }

    @Transactional(readOnly = true)
    public List<ClubDto.SearchRes> searchList(String region, String sportCategory) {
        List<Club> club = clubRepository.findByDeleted(false).orElseThrow(() -> new EntityNotFoundException("searchList Error"));
        return club.stream()
                .filter(c -> region == null || region.isEmpty() || region.contains(c.getRegion()))
                .filter(c -> sportCategory == null || sportCategory.isEmpty() || sportCategory.contains(c.getSportCategory()))
                .map(ClubDto.SearchRes :: from)
                .toList();
    }

}
