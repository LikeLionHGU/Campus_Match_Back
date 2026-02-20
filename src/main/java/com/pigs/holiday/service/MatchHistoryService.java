package com.pigs.holiday.service;


import com.pigs.holiday.domain.*;
import com.pigs.holiday.dto.GalleryDto;
import com.pigs.holiday.dto.MatchHistoryDto;
import com.pigs.holiday.exception.NoPermissionException;
import com.pigs.holiday.mapper.MatchHistoryMapper;
import com.pigs.holiday.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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

    final MatchHistoryMapper matchHistoryMapper;

    @Transactional
    public MatchHistoryDto.CreateResDto create(MatchHistoryDto.CreateReqDto createReqDto, Long requestClubId) {
        Club homeClub = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("MatchHistory Create Error"));
        Club awayClub = clubRepository.findById(createReqDto.getAwayClubId()).orElseThrow(() -> new EntityNotFoundException("MatchHistory Create Error"));

        homeClub.setTotalMatches(homeClub.getTotalMatches() + 1);
        if(createReqDto.getMatchType()){
            switch (createReqDto.getResult()) {
                case "승":
                    homeClub.setTotalWins(homeClub.getTotalWins()+1);
                    break;
                case "패":
                    homeClub.setTotalLosses(homeClub.getTotalLosses()+1);
                    break;
                case "무":
                    homeClub.setTotalDraws(homeClub.getTotalDraws()+1);
                    break;
            }
        }

        return MatchHistoryDto.CreateResDto.toCreateResDto(matchHistoryRepository.save(createReqDto.toEntity(homeClub, awayClub)));
    }

    @Transactional(readOnly = true)
    public MatchHistoryDto.HistoryResDto list(Long clubId, MatchHistoryDto.ListReqDto listReqDto, Long requestClubId) {
        MatchHistoryDto.HistoryResDto historyResDto = MatchHistoryDto.HistoryResDto.toHistoryResDto(clubId.equals(requestClubId));
        historyResDto.setMatchHistoryList(matchHistoryMapper.list(clubId, listReqDto));
        return historyResDto;
    }

    @Transactional(readOnly = true)
    public MatchHistoryDto.HistoryResDto createList(Long clubId, MatchHistoryDto.ListReqDto listReqDto, Long requestClubId) {
        MatchHistoryDto.HistoryResDto historyResDto = MatchHistoryDto.HistoryResDto.toHistoryResDto(clubId.equals(requestClubId));
        historyResDto.setMatchHistoryList(matchHistoryMapper.createList(clubId, listReqDto));
        return historyResDto;
    }

    @Transactional(readOnly = true)
    public MatchHistoryDto.HistoryResDto addList(Long clubId, MatchHistoryDto.ListReqDto listReqDto, Long requestClubId) {
        MatchHistoryDto.HistoryResDto historyResDto = MatchHistoryDto.HistoryResDto.toHistoryResDto(clubId.equals(requestClubId));
        historyResDto.setMatchHistoryList(matchHistoryMapper.addList(clubId, listReqDto));
        return historyResDto;
    }

    @Transactional(readOnly = true)
    public MatchHistoryDto.DetailResDto detail(Long matchHistoryId) {
        MatchHistory matchHistory = matchHistoryRepository.findById(matchHistoryId).orElseThrow(() -> new EntityNotFoundException("MatchHistory Detail Error"));

        return MatchHistoryDto.DetailResDto.toDetailResDto(matchHistory);
    }

    @Transactional
    public MatchHistoryDto.UpdateResDto update(Long matchHistoryId, MatchHistoryDto.UpdateReqDto updateReqDto, Long requestClubId) {
        Club homeClub = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("MatchHistory Update Error"));
        MatchHistory matchHistory = matchHistoryRepository.findById(matchHistoryId).orElseThrow(() -> new EntityNotFoundException("MatchHistory Update Error"));

        if (!matchHistory.getHomeClub().getId().equals(requestClubId)) {
            throw new NoPermissionException("MatchHistory Update Error");
        }

        if(updateReqDto.getMatchDate()!=null && !updateReqDto.getMatchDate().equals(matchHistory.getMatchDate())) {
            matchHistory.setMatchDate(updateReqDto.getMatchDate());
        }
        if(StringUtils.hasText(updateReqDto.getLocation()) && !updateReqDto.getLocation().equals(matchHistory.getLocation())) {
            matchHistory.setLocation(updateReqDto.getLocation());
        }
        if(updateReqDto.getAwayClubId()!=null && !updateReqDto.getAwayClubId().equals(matchHistory.getAwayClub().getId())) {
            Club awayClub = clubRepository.findById(updateReqDto.getAwayClubId()).orElseThrow(() -> new EntityNotFoundException("MatchHistory Update Error"));
            matchHistory.setAwayClub(awayClub);
        }
        if(updateReqDto.getMatchType()!=null) {
            matchHistory.setMatchType(updateReqDto.getMatchType());
            switch (matchHistory.getResult()) {
                case "승":
                    homeClub.setTotalWins(homeClub.getTotalWins()-1);
                    break;
                case "패":
                    homeClub.setTotalLosses(homeClub.getTotalLosses()-1);
                    break;
                case "무":
                    homeClub.setTotalDraws(homeClub.getTotalDraws()-1);
                    break;
            }
            matchHistory.setResult(updateReqDto.getResult());
        }

        if(matchHistory.getMatchType()){
            switch (matchHistory.getResult()) {
                case "승":
                    homeClub.setTotalWins(homeClub.getTotalWins()+1);
                    break;
                case "패":
                    homeClub.setTotalLosses(homeClub.getTotalLosses()+1);
                    break;
                case "무":
                    homeClub.setTotalDraws(homeClub.getTotalDraws()+1);
                    break;
            }
        }

        return MatchHistoryDto.UpdateResDto.toUpdateResDto(matchHistoryRepository.save(matchHistory));
    }

    @Transactional
    public MatchHistoryDto.DeleteResDto delete(Long matchHistoryId, Long requestClubId) {
        Club homeClub = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("MatchHistory Delete Error"));
        MatchHistory matchHistory = matchHistoryRepository.findById(matchHistoryId).orElseThrow(() -> new EntityNotFoundException("History Delete Error"));
        if(!matchHistory.getHomeClub().getId().equals(requestClubId)) {
            throw new NoPermissionException("History Delete Error");
        }

        homeClub.setTotalMatches(homeClub.getTotalMatches()-1);
        if(matchHistory.getMatchType()){
            switch (matchHistory.getResult()) {
                case "승":
                    homeClub.setTotalWins(homeClub.getTotalWins()-1);
                    break;
                case "패":
                    homeClub.setTotalLosses(homeClub.getTotalLosses()-1);
                    break;
                case "무":
                    homeClub.setTotalDraws(homeClub.getTotalDraws()-1);
                    break;
            }
        }

        matchHistoryRepository.delete(matchHistory);

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
