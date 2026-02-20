package com.pigs.holiday.service;


import com.pigs.holiday.domain.*;
import com.pigs.holiday.dto.GalleryDto;
import com.pigs.holiday.dto.MatchHistoryDto;
import com.pigs.holiday.exception.NoPermissionException;
import com.pigs.holiday.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class MatchHistoryService {

    final ClubRepository clubRepository;
    final MatchHistoryRepository matchHistoryRepository;
    final MatchPostRepository matchPostRepository;
    final NotificationRepository notificationRepository;
    final MatchRequestRepository matchRequestRepository;

    final GalleryService galleryService;

    public MatchHistoryDto.CreateResDto create(MatchHistoryDto.CreateReqDto createReqDto, Long requestClubId) {
        Club homeClub = clubRepository.findById(requestClubId)
                .orElseThrow(() -> new EntityNotFoundException("Home Club Not Found"));

        Club awayClub = clubRepository.findById(createReqDto.getOppositionClubId())
                .orElseThrow(() -> new EntityNotFoundException("Away Club Not Found"));

        MatchHistory matchHistory = createReqDto.toEntity(homeClub, awayClub);
        return MatchHistoryDto.CreateResDto.toCreateResDto(matchHistoryRepository.save(matchHistory));

    }


    @Transactional(readOnly = true)
    public List<MatchHistoryDto.ListResDto> list(Long clubId) {
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("MatchHistory List Error"));
        List<MatchHistory> homeClubList = matchHistoryRepository.findByHomeClub(club);
        List<MatchHistory> awayClubList = matchHistoryRepository.findByAwayClub(club);

        List<MatchHistoryDto.ListResDto> resDtoList = new ArrayList<>();

        resDtoList.addAll(homeClubList.stream().map(MatchHistoryDto.ListResDto::toHomeResDto).toList());
        resDtoList.addAll(awayClubList.stream().map(MatchHistoryDto.ListResDto::toAwayResDto).toList());

        return resDtoList;
    }

    @Transactional
    public MatchHistoryDto.UpdateResDto update(MatchHistoryDto.UpdateReqDto reqDto, Long requestClubId) {

        MatchHistory matchHistory = matchHistoryRepository.findById(reqDto.getMatchHistoryId())
                .orElseThrow(() -> new EntityNotFoundException("MatchHistory Not Found"));

        if (!matchHistory.getHomeClub().getId().equals(requestClubId)) { throw new IllegalArgumentException("수정 권한이 없습니다.");}

        if (reqDto.getOppositionClubId() != null) {
            Club newOpponent = clubRepository.findById(reqDto.getOppositionClubId())
                    .orElseThrow(() -> new EntityNotFoundException("Opposition Club Not Found"));
            matchHistory.setAwayClub(newOpponent);
        }

        if (reqDto.getMatchDate() != null) {
            matchHistory.setMatchDate(reqDto.getMatchDate());
        }

        if (reqDto.getMatchType() != null) {
            matchHistory.setMatchType(reqDto.getMatchType());
        }

        if (reqDto.getResult() != null && !reqDto.getResult().isBlank()) {
            matchHistory.setResult(reqDto.getResult());
        }
        return MatchHistoryDto.UpdateResDto.builder()
                .matchHistoryId(matchHistory.getId())
                .build();
    }

    public MatchHistoryDto.DeleteResDto delete(Long matchHistoryId, Long clubId) {
        MatchHistory matchHistory = matchHistoryRepository.findById(matchHistoryId).orElseThrow(() -> new EntityNotFoundException("History Delete Error"));

        if(matchHistory.getHomeClub().getId().equals(clubId)||matchHistory.getAwayClub().getId().equals(clubId)) {
            matchHistoryRepository.delete(matchHistory);
        }else{
            throw new NoPermissionException("History Delete Error");
        }

        return MatchHistoryDto.DeleteResDto.toDeleteResDto(matchHistory);
    }

    // Finish
    @Transactional
    public MatchHistoryDto.FinishResDto finish(Long matchPostId, MatchHistoryDto.FinishReqDto finishReqDto, List<String> imageUrls, Long requestClubId) {
        Club requestClub = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("Gallery Finish Error"));
        MatchPost matchPost = matchPostRepository.findById(matchPostId).orElseThrow(() -> new EntityNotFoundException("MatchPost Finish Error"));
        Club awayClub = matchPost.getHomeClub().getId().equals(requestClubId) ? matchPost.getAwayClub() : matchPost.getHomeClub();

        if(!matchPost.getAwayClub().getId().equals(requestClubId) && !matchPost.getHomeClub().getId().equals(requestClubId)) {
            throw new NoPermissionException("MatchPost Finish Error");
        }

        if(matchPost.getFinishClub()!= null && matchPost.getFinishClub().getId().equals(requestClubId)) {
            throw new NoPermissionException("MatchPost Finish Error");
        }

        MatchHistory matchHistory = finishReqDto.toEntity(requestClub, awayClub, matchPost);

        requestClub.setTotalMatches(requestClub.getTotalMatches()+1);
        if(finishReqDto.getMatchType()){
            switch (finishReqDto.getResult()) {
                case "승":
                    requestClub.setTotalWins(requestClub.getTotalWins()+1);
                    break;
                case "패":
                    requestClub.setTotalLosses(requestClub.getTotalLosses()+1);
                    break;
                case "무":
                    requestClub.setTotalDraws(requestClub.getTotalDraws()+1);
                    break;
            }
        }

        if(finishReqDto.getMannerScore()){
            awayClub.setMannerScore(awayClub.getMannerScore()+1);
        }else{
            awayClub.setMannerScore(awayClub.getMannerScore()-1);
        }

        if(finishReqDto.getRematch()){
            LocalDate today = LocalDate.now();
            Notification rematchNotification = Notification.of("rematch", today, "", false, awayClub, requestClub, null);
            notificationRepository.save(rematchNotification);
            Notification remindWeekNotification = Notification.of("remind", today.plusWeeks(1), "", false, awayClub, requestClub, null);
            notificationRepository.save(remindWeekNotification);
            Notification remindMonthNotification = Notification.of("remind", today.plusMonths(1), "", false, awayClub, requestClub, null);
            notificationRepository.save(remindMonthNotification);
        }

        if(matchPost.getFinishClub()==null) {
            matchPost.setFinishClub(requestClub);
        }else{
            matchRequestRepository.deleteByMatchPost(matchPost);
            matchPostRepository.delete(matchPost);
        }

        galleryService.finish(GalleryDto.CreateReqDto.toCreateReqDto(finishReqDto, matchHistory), imageUrls, requestClubId);

        return MatchHistoryDto.FinishResDto.toFinishResDto(matchHistoryRepository.save(matchHistory));
    }

}
