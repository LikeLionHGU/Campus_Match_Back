package com.pigs.holiday.service;

import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.MatchPost;
import com.pigs.holiday.dto.MatchPostDto;
import com.pigs.holiday.exception.NoPermissionException;
import com.pigs.holiday.mapper.MatchPostMapper;
import com.pigs.holiday.repository.ClubRepository;
import com.pigs.holiday.repository.MatchPostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MatchPostService {

    final MatchPostRepository matchPostRepository;
    final ClubRepository clubRepository;

    final MatchPostMapper matchPostMapper;

    // Create
    public MatchPostDto.CreateResDto create(MatchPostDto.CreateReqDto createReqDto, Long requestClubId){
        Club requestClub = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("MatchPost Create Error"));
        MatchPost matchPost = createReqDto.toEntity(requestClub);

        return MatchPostDto.CreateResDto.toCreateResDto(matchPostRepository.save(matchPost));
    }

    // List
    @Transactional(readOnly = true)
    public List<MatchPostDto.ListResDto> list(MatchPostDto.ListReqDto listReqDto){
        if (listReqDto.getStartDate() == null || listReqDto.getStartDate().isBefore(LocalDate.now())) {
            listReqDto.setStartDate(LocalDate.now());
        }
        return matchPostMapper.list(listReqDto);
    }

    // MineList
    @Transactional(readOnly = true)
    public List<MatchPostDto.ListResDto> mineList(MatchPostDto.ListReqDto listReqDto, Long requestClubId){
        if (listReqDto.getStartDate() == null || listReqDto.getStartDate().isBefore(LocalDate.now())) {
            listReqDto.setStartDate(LocalDate.now());
        }
        return matchPostMapper.mineList(listReqDto, requestClubId);
    }

    // OtherList
    @Transactional(readOnly = true)
    public List<MatchPostDto.ListResDto> otherList(MatchPostDto.ListReqDto listReqDto, Long requestClubId){
        if (listReqDto.getStartDate() == null || listReqDto.getStartDate().isBefore(LocalDate.now())) {
            listReqDto.setStartDate(LocalDate.now());
        }
        return matchPostMapper.otherList(listReqDto, requestClubId);
    }

    // Detail
    @Transactional(readOnly = true)
    public MatchPostDto.DetailResDto detail(Long matchPostId, Long requestClubId){
        MatchPost matchPost = matchPostRepository.findById(matchPostId).orElseThrow(() -> new EntityNotFoundException("MatchPost Detail Error"));

        return MatchPostDto.DetailResDto.toDetailResDto(matchPost, matchPost.getHomeClub().getId().equals(requestClubId));
    }

    // Update
    @Transactional
    public MatchPostDto.UpdateResDto update(Long matchPostId, MatchPostDto.UpdateReqDto updateReqDto, Long requestClubId){
        MatchPost matchPost = matchPostRepository.findById(matchPostId).orElseThrow(() -> new EntityNotFoundException("MatchPost Update Error"));
        if(!matchPost.getHomeClub().getId().equals(requestClubId)){
            throw new NoPermissionException("MatchPost Update Error");
        }else if(matchPost.getStatus() || matchPost.getDeleted()){
            throw new RuntimeException("MatchPost Update Error");
        }

        if(StringUtils.hasText(updateReqDto.getSportCategory()) && !updateReqDto.getSportCategory().equals(matchPost.getSportCategory())){
            matchPost.setSportCategory(updateReqDto.getSportCategory());
        }
        if(updateReqDto.getMatchDate() != null && !updateReqDto.getMatchDate().equals(matchPost.getMatchDate())){
            matchPost.setMatchDate(updateReqDto.getMatchDate());
        }
        if(StringUtils.hasText(updateReqDto.getLocation()) && !updateReqDto.getLocation().equals(matchPost.getLocation())){
            matchPost.setLocation(updateReqDto.getLocation());
        }
        if(StringUtils.hasText(updateReqDto.getLocationDetail()) && !updateReqDto.getLocationDetail().equals(matchPost.getLocationDetail())){
            matchPost.setLocationDetail(updateReqDto.getLocationDetail());
        }
        if(updateReqDto.getStartTime() != null && !updateReqDto.getStartTime().equals(matchPost.getStartTime())){
            matchPost.setStartTime(updateReqDto.getStartTime());
        }
        if(updateReqDto.getEndTime() != null && !updateReqDto.getEndTime().equals(matchPost.getEndTime())){
            matchPost.setEndTime(updateReqDto.getEndTime());
        }
        if(StringUtils.hasText(updateReqDto.getContent()) && !updateReqDto.getContent().equals(matchPost.getContent())){
            matchPost.setContent(updateReqDto.getContent());
        }

        return MatchPostDto.UpdateResDto.toUpdateResDto(matchPost);
    }

    // Delete
    @Transactional
    public MatchPostDto.DeleteResDto delete(Long matchPostId, Long requestClubId){
        MatchPost matchPost = matchPostRepository.findById(matchPostId).orElseThrow(() -> new EntityNotFoundException("MatchPost Update Error"));
        if(!matchPost.getHomeClub().getId().equals(requestClubId)) {
            throw new NoPermissionException("MatchPost Delete Error");
        }

        matchPost.setDeleted(true);

        return MatchPostDto.DeleteResDto.builder().matchPostId(matchPost.getId()).build();
    }

    // UpcomingDashboard
    @Transactional(readOnly = true)
    public List<MatchPostDto.DashboardResDto> upcomingDashboard(Long clubId){
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("MatchPost UpcomingDashboard Error"));

        LocalDate today = LocalDate.now();
        List<MatchPost> matchPostHomeList = matchPostRepository.findByHomeClubAndDeletedAndStatusAndMatchDateGreaterThanOrderByMatchDateDesc(club,false, true, today);
        List<MatchPost> matchPostAwayList = matchPostRepository.findByAwayClubAndDeletedAndStatusAndMatchDateGreaterThanOrderByMatchDateDesc(club,false, true, today);

        List<MatchPostDto.DashboardResDto> dashboardListResDtoList = new ArrayList<>();

        dashboardListResDtoList.addAll(matchPostHomeList.stream().map(MatchPostDto.DashboardResDto::toDashboardHomeResDto).toList());
        dashboardListResDtoList.addAll(matchPostAwayList.stream().map(MatchPostDto.DashboardResDto::toDashboardAwayResDto).toList());

        dashboardListResDtoList.sort(Comparator.comparing(MatchPostDto.DashboardResDto::getMatchDate));

        return dashboardListResDtoList;
    }

    // OngoingDashboard
    @Transactional(readOnly = true)
    public List<MatchPostDto.DashboardResDto> ongoingDashboard(Long clubId){
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("MatchPost UpcomingDashboard Error"));

        LocalDate today = LocalDate.now();
        List<MatchPost> matchPostHomeList = matchPostRepository.findByHomeClubAndDeletedAndStatusAndMatchDate(club,false, true, today);
        List<MatchPost> matchPostAwayList = matchPostRepository.findByAwayClubAndDeletedAndStatusAndMatchDate(club,false, true, today);

        List<MatchPostDto.DashboardResDto> dashboardListResDtoList = new ArrayList<>();

        dashboardListResDtoList.addAll(matchPostHomeList.stream().map(MatchPostDto.DashboardResDto::toDashboardHomeResDto).toList());
        dashboardListResDtoList.addAll(matchPostAwayList.stream().map(MatchPostDto.DashboardResDto::toDashboardAwayResDto).toList());

        return dashboardListResDtoList;
    }

    // UpcomingList
    @Transactional(readOnly = true)
    public List<MatchPostDto.ListResDto> upcomingList(Long clubId, Long requestClubId){
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("MatchPost UpcomingList Error"));

        LocalDate today = LocalDate.now();
        List<MatchPost> matchPostHomeList = matchPostRepository.findByHomeClubAndDeletedAndStatusAndMatchDateGreaterThanOrderByMatchDateDesc(club,false, true, today);
        List<MatchPost> matchPostAwayList = matchPostRepository.findByAwayClubAndDeletedAndStatusAndMatchDateGreaterThanOrderByMatchDateDesc(club,false, true, today);

        List<MatchPostDto.ListResDto> listResDtoList = new ArrayList<>();

        listResDtoList.addAll(matchPostHomeList.stream().map(MatchPostDto.ListResDto::toHomeListResDto).toList());
        listResDtoList.addAll(matchPostAwayList.stream().map(MatchPostDto.ListResDto::toAwayListResDto).toList());

        listResDtoList.sort(Comparator.comparing(MatchPostDto.ListResDto::getMatchDate));

        return listResDtoList;
    }

    // UpcomingDetail
    @Transactional(readOnly = true)
    public MatchPostDto.IngDetailResDto upcomingDetail(Long clubId, Long matchPostId){
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("MatchPost UpcomingDetail Error"));
        MatchPost matchPost = matchPostRepository.findById(matchPostId).orElseThrow(() -> new EntityNotFoundException("MatchPost UpcomingDetail Error"));

        if(club.equals(matchPost.getHomeClub())){
            return MatchPostDto.IngDetailResDto.toIngHomeDetailResDto(matchPost);
        }else if(club.equals(matchPost.getAwayClub())){
            return MatchPostDto.IngDetailResDto.toIngAwayDetailResDto(matchPost);
        }else{
            throw new NoPermissionException("MatchPost UpcomingDetail Error");
        }
    }

    // UpcomingDelete
    @Transactional
    public MatchPostDto.DeleteResDto upcomingDelete(Long matchPostId,MatchPostDto.UpcomingDeleteReqDto upcomingDeleteReqDto, Long requestClubId){
        MatchPost matchPost = matchPostRepository.findById(matchPostId).orElseThrow(() -> new EntityNotFoundException("MatchPost UpcomingDelete Error"));
        if(!matchPost.getHomeClub().getId().equals(requestClubId) || !matchPost.getAwayClub().getId().equals(requestClubId)) {
            throw new NoPermissionException("MatchPost UpcomingDelete Error");
        }

        matchPost.setStatus(false);
        matchPost.setDeleted(true);

        // notification 생성

        return MatchPostDto.DeleteResDto.builder().matchPostId(matchPost.getId()).build();
    }

    // OngoingList
    @Transactional(readOnly = true)
    public List<MatchPostDto.ListResDto> ongoingList(Long clubId, Long requestClubId){
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("MatchPost OngoingList Error"));

        LocalDate today = LocalDate.now();
        List<MatchPost> matchPostHomeList = matchPostRepository.findByHomeClubAndDeletedAndStatusAndMatchDate(club, false, true, today);
        List<MatchPost> matchPostAwayList = matchPostRepository.findByAwayClubAndDeletedAndStatusAndMatchDate(club, false, true, today);

        List<MatchPostDto.ListResDto> listResDtoList = new ArrayList<>();

        listResDtoList.addAll(matchPostHomeList.stream().map(MatchPostDto.ListResDto::toHomeListResDto).toList());
        listResDtoList.addAll(matchPostAwayList.stream().map(MatchPostDto.ListResDto::toAwayListResDto).toList());

        return listResDtoList;
    }

    // OngoingDetail
    @Transactional(readOnly = true)
    public MatchPostDto.IngDetailResDto ongoingDetail(Long clubId, Long matchPostId){
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("MatchPost OngoingDetail Error"));
        MatchPost matchPost = matchPostRepository.findById(matchPostId).orElseThrow(() -> new EntityNotFoundException("MatchPost OngoingDetail Error"));

        if(club.equals(matchPost.getHomeClub())){
            return MatchPostDto.IngDetailResDto.toIngHomeDetailResDto(matchPost);
        }else if(club.equals(matchPost.getAwayClub())){
            return MatchPostDto.IngDetailResDto.toIngAwayDetailResDto(matchPost);
        }else{
            throw new RuntimeException("MatchPost OngoingDetail Error");
        }
    }

    // OngoingDelete
    @Transactional
    public MatchPostDto.DeleteResDto ongoingDelete(Long matchPostId, Long requestClubId){
        MatchPost matchPost = matchPostRepository.findById(matchPostId).orElseThrow(() -> new EntityNotFoundException("MatchPost OngoingDelete Error"));
        if(!matchPost.getHomeClub().getId().equals(requestClubId) || !matchPost.getAwayClub().getId().equals(requestClubId)) {
            throw new NoPermissionException("MatchPost OngoingDelete Error");
        }

        matchPost.setDeleted(true);

        // notification

        return MatchPostDto.DeleteResDto.builder().matchPostId(matchPost.getId()).build();
    }

    // FinishList
    @Transactional
    public List<MatchPostDto.ListResDto> finishList(Long requestClubId){
        LocalDate today = LocalDate.now();

        List<MatchPost> matchPostList = matchPostRepository.findByStatusAndDeletedAndMatchDateLessThan(true, false, today);
        for(MatchPost matchPost : matchPostList){
            matchPost.setDeleted(true);
        }

        Club club = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("MatchPost FinishList Error"));
        List<MatchPost> matchPostHomeList = matchPostRepository.findByHomeClubAndStatusAndDeleted(club, true, true);
        List<MatchPost> matchPostAwayList = matchPostRepository.findByAwayClubAndStatusAndDeleted(club, true, true);

        List<MatchPostDto.ListResDto> listResDtoList = new ArrayList<>();
        listResDtoList.addAll(matchPostHomeList.stream().map(MatchPostDto.ListResDto::toHomeListResDto).toList());
        listResDtoList.addAll(matchPostAwayList.stream().map(MatchPostDto.ListResDto::toAwayListResDto).toList());

        return listResDtoList;
    }

    // FinishDetail
    @Transactional(readOnly = true)
    public MatchPostDto.FinishDetailResDto finishDetail(Long matchPostId, Long requestClubId){
        Club club = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("MatchPost OngoingDetail Error"));
        MatchPost matchPost = matchPostRepository.findById(matchPostId).orElseThrow(() -> new EntityNotFoundException("MatchPost OngoingDetail Error"));

        if(club.equals(matchPost.getHomeClub())){
            return MatchPostDto.FinishDetailResDto.toFinishHomeDetailResDto(matchPost);
        }else if(club.equals(matchPost.getAwayClub())){
            return MatchPostDto.FinishDetailResDto.toFinishAwayDetailResDto(matchPost);
        }else{
            throw new NoPermissionException("MatchPost FinishDetail Error");
        }
    }

    // ScheduleList
    @Transactional(readOnly = true)
    public List<MatchPostDto.ScheduleResDto> scheduleList(Long clubId, Long requestClubId){
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("MatchPost Schedule Error"));
        List<MatchPost> matchPostHomeListFalse = matchPostRepository.findByHomeClubAndDeletedAndStatus(club, false, false);

        List<MatchPostDto.ScheduleResDto> scheduleResDtoList = new ArrayList<>(matchPostHomeListFalse.stream().map(MatchPostDto.ScheduleResDto::toHomeResDto).toList());

        if(clubId.equals(requestClubId)){
            List<MatchPost> matchPostHomeListTrue = matchPostRepository.findByHomeClubAndDeletedAndStatus(club, false, true);
            List<MatchPost> matchPostAwayList = matchPostRepository.findByAwayClubAndDeletedAndStatus(club, false, true);
            scheduleResDtoList.addAll(matchPostHomeListTrue.stream().map(MatchPostDto.ScheduleResDto::toHomeResDto).toList());
            scheduleResDtoList.addAll(matchPostAwayList.stream().map(MatchPostDto.ScheduleResDto::toAwayResDto).toList());
        }

        return  scheduleResDtoList;
    }

    // ScheduleDetail
    @Transactional(readOnly = true)
    public MatchPostDto.ScheduleDetailResDto scheduleDetail(Long clubId, Long matchPostId, Long requestClubId){
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("MatchPost ScheduleDetail Error"));
        MatchPost matchPost = matchPostRepository.findById(matchPostId).orElseThrow(() -> new EntityNotFoundException("MatchPost ScheduleDetail Error"));

        if(club.equals(matchPost.getHomeClub())){
            return MatchPostDto.ScheduleDetailResDto.toScheduleHomeDetailDto(matchPost, club.getId().equals(requestClubId));
        }else if(club.equals(matchPost.getAwayClub())){
            return MatchPostDto.ScheduleDetailResDto.toScheduleAwayDetailDto(matchPost, club.getId().equals(requestClubId));
        }else{
            throw new RuntimeException("MatchPost ScheduleDetail Error");
        }
    }
}
