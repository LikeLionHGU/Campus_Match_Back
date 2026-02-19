package com.pigs.holiday.service;

import com.pigs.holiday.domain.Award;
import com.pigs.holiday.mapper.ClubMapper;
import com.pigs.holiday.repository.AwardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import com.pigs.holiday.domain.Club;
import com.pigs.holiday.dto.ClubDto;
import com.pigs.holiday.repository.ClubRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClubService {

    final ClubRepository clubRepository;
    final AwardRepository awardRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final MatchPostService matchPostService;
    private final MatchRequestService matchRequestService;
    private final ScheduleService scheduleService;
    private final GalleryService galleryService;
    private final ClubMapper clubMapper;

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

    // Dashboard
    @Transactional(readOnly = true)
    public ClubDto.DashboardResDto dashboard(Long clubId, Long requestClubId) {
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("Club Dashboard Error"));

        ClubDto.DashboardResDto dashboardResDto = ClubDto.DashboardResDto.toDashboardResDto(club);
        dashboardResDto.setIsMine(clubId.equals(requestClubId));
        dashboardResDto.setUpcomingResDtoList(matchPostService.upcomingDashboard(clubId));
        dashboardResDto.setOngoingResDtoList(matchPostService.ongoingDashboard(clubId));
        dashboardResDto.setReceiveResDtoList(matchRequestService.receiveDashboard(clubId));
        dashboardResDto.setSendResDtoList(matchRequestService.sendDashboard(clubId));
        if(dashboardResDto.getIsMine()){
            dashboardResDto.setScheduleResDtoList(scheduleService.scheduleDashboard(clubId));
        }
        dashboardResDto.setGalleryResDtoList(galleryService.galleryDashboard(clubId));

        return dashboardResDto;
    }

    // Description
    @Transactional(readOnly = true)
    public ClubDto.DescriptionResDto description(Long clubId, Long requestClubId) {
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("Club Description Error"));
        List<Award> awardList = club.getAwardList();

        return ClubDto.DescriptionResDto.toDescriptionResDto(club, awardList.stream().map(ClubDto.AwardResDto::toAwardResDto).toList(), clubId.equals(requestClubId));
    }

    // Description Update
    @Transactional
    public ClubDto.DescriptionUpdateResDto descriptionUpdate(ClubDto.DescriptionUpdateReqDto descriptionUpdateReqDto, Long requestClubId) {
        Club requestClub = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("Club Description Error"));

        if(StringUtils.hasText(descriptionUpdateReqDto.getDescription()) && !descriptionUpdateReqDto.getDescription().equals(requestClub.getDescription())) {
            requestClub.setDescription(descriptionUpdateReqDto.getDescription());
        }

        return ClubDto.DescriptionUpdateResDto.toDescriptionUpdateResDto(requestClub);
    }

    // Award Create
    public ClubDto.AwardResDto award(ClubDto.AwardReqDto awardReqDto, Long requestClubId) {
        Club requestClub = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("Club Award Create Error"));

        return ClubDto.AwardResDto.toAwardResDto(awardRepository.save(awardReqDto.toEntity(requestClub)));
    }

    // Award Create
    @Transactional
    public ClubDto.AwardDeleteResDto awardDelete(Long awardId, Long requestClubId) {
        Award award = awardRepository.findById(awardId).orElseThrow(() -> new EntityNotFoundException("Award Delete Error"));
        if(award.getClub().getId().equals(requestClubId)) {
            awardRepository.deleteById(awardId);
        }

        return ClubDto.AwardDeleteResDto.toAwardDeleteResDto(award);
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

        if(StringUtils.hasText(settingUpdateReqDto.getUsername()) && !settingUpdateReqDto.getUsername().equals(club.getUsername())) {
            club.setUsername(settingUpdateReqDto.getUsername());
        }
        if(StringUtils.hasText(settingUpdateReqDto.getPassword()) && !settingUpdateReqDto.getPassword().equals(club.getPassword())) {
            club.setPassword(bCryptPasswordEncoder.encode(settingUpdateReqDto.getPassword()));
        }
        if(StringUtils.hasText(settingUpdateReqDto.getName()) && !settingUpdateReqDto.getName().equals(club.getName())) {
            club.setName(settingUpdateReqDto.getName());
        }
        if(StringUtils.hasText(settingUpdateReqDto.getUniversity()) && !settingUpdateReqDto.getUniversity().equals(club.getUniversity())) {
            club.setUniversity(settingUpdateReqDto.getUniversity());
        }
        if(StringUtils.hasText(settingUpdateReqDto.getPhone()) && !settingUpdateReqDto.getPhone().equals(club.getPhone())) {
            club.setPhone(settingUpdateReqDto.getPhone());
        }
        if(StringUtils.hasText(settingUpdateReqDto.getEmail()) && !settingUpdateReqDto.getEmail().equals(club.getEmail())) {
            club.setEmail(settingUpdateReqDto.getEmail());
        }
        if(StringUtils.hasText(settingUpdateReqDto.getClubName()) && !settingUpdateReqDto.getClubName().equals(club.getClubName())) {
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

    // List
    @Transactional(readOnly = true)
    public List<ClubDto.ListResDto> list(ClubDto.ListReqDto listReqDto, Long requestClubId) {
        return clubMapper.list(listReqDto, requestClubId);
    }

}
