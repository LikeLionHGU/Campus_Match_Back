package com.pigs.holiday.service;

import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.MatchPost;
import com.pigs.holiday.domain.MatchRequest;
import com.pigs.holiday.domain.Notification;
import com.pigs.holiday.dto.MatchRequestDto;
import com.pigs.holiday.exception.NoPermissionException;
import com.pigs.holiday.repository.ClubRepository;
import com.pigs.holiday.repository.MatchPostRepository;
import com.pigs.holiday.repository.MatchRequestRepository;
import com.pigs.holiday.repository.NotificationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MatchRequestService {

    final MatchRequestRepository matchRequestRepository;
    final ClubRepository clubRepository;
    final MatchPostRepository matchPostRepository;
    final NotificationRepository notificationRepository;

    // Create
    public MatchRequestDto.CreateResDto create(Long matchPostId, MatchRequestDto.CreateReqDto createReqDto, Long requestClubId){
        Club requestClub = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("MatchRequest Create Error"));
        MatchPost matchPost = matchPostRepository.findById(matchPostId).orElseThrow(() -> new EntityNotFoundException("MatchRequest Create Error"));
        MatchRequest matchRequest = createReqDto.toEntity(matchPost, requestClub);

        LocalDate today = LocalDate.now();
        Notification notification = Notification.of("receive", today, "", false, matchPost.getHomeClub(), matchRequest.getSenderClub());
        notificationRepository.save(notification);

        return MatchRequestDto.CreateResDto.toCreateResDto(matchRequestRepository.save(matchRequest));
    }

    // ReceiveDashboardList
    @Transactional(readOnly = true)
    public List<MatchRequestDto.DashboardResDto> receiveDashboard(Long clubId){
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("MatchRequest ReceiveDashboardList Error"));
        List<MatchPost> matchPostList = matchPostRepository.findByHomeClubAndStatusAndDeleted(club, false, false);

        List<MatchRequest> matchRequestList = new ArrayList<>();

        for (MatchPost matchPost : matchPostList) {
            matchRequestList.addAll(matchPost.getMatchRequestList());
        }

        return matchRequestList.stream().map(MatchRequestDto.DashboardResDto::toDashboardReceiveResDto).toList();
    }

    // SendDashboardList
    @Transactional(readOnly = true)
    public List<MatchRequestDto.DashboardResDto> sendDashboard(Long clubId){
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("MatchRequest SendDashboardList Error"));
        List<MatchRequest> matchRequestList = matchRequestRepository.findBySenderClubAndDeleted(club, false);

        return matchRequestList.stream().map(MatchRequestDto.DashboardResDto::toDashboardSendResDto).toList();
    }

    // ReceiveList
    @Transactional(readOnly = true)
    public List<MatchRequestDto.ListResDto> receiveList(Long requestClubId){
        Club requestClub = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("MatchRequest ReceiveList Error"));
        List<MatchPost> matchPostList = matchPostRepository.findByHomeClubAndStatusAndDeleted(requestClub, false, false);

        List<MatchRequest> matchRequestList = new ArrayList<>();

        for (MatchPost matchPost : matchPostList) {
            matchRequestList.addAll(matchPost.getMatchRequestList());
        }

        return matchRequestList.stream().map(MatchRequestDto.ListResDto::toReceiveListResDto).toList();
    }

    // ReceiveDetail
    @Transactional(readOnly = true)
    public MatchRequestDto.DetailResDto receiveDetail(Long matchRequestId){
        MatchRequest matchRequest = matchRequestRepository.findById(matchRequestId).orElseThrow(() -> new EntityNotFoundException("MatchRequest ReceiveDetail Error"));
        return MatchRequestDto.DetailResDto.toReceiveDetailResDto(matchRequest);
    }

    // ReceiveDelete
    @Transactional
    public MatchRequestDto.DeleteResDto receiveDelete(Long matchRequestId, MatchRequestDto.DeleteReqDto deleteReqDto, Long requestClubId){
        MatchRequest matchRequest = matchRequestRepository.findById(matchRequestId).orElseThrow(() -> new EntityNotFoundException("MatchRequest ReceiveDelete Error"));
        if(!matchRequest.getMatchPost().getHomeClub().getId().equals(requestClubId)) {
            throw new NoPermissionException("MatchRequest ReceiveDelete Error");
        }

        matchRequestRepository.deleteById(matchRequestId);

        LocalDate today = LocalDate.now();
        Notification notification = Notification.of("sendNo", today, deleteReqDto.getContent(), false, matchRequest.getSenderClub(), matchRequest.getMatchPost().getHomeClub());
        notificationRepository.save(notification);

        return MatchRequestDto.DeleteResDto.builder().matchRequestId(matchRequest.getId()).build();
    }

    // ReceiveUpdate
    @Transactional
    public MatchRequestDto.UpdateResDto receiveUpdate(Long matchRequestId, Long requestClubId){
        MatchRequest matchRequest = matchRequestRepository.findById(matchRequestId).orElseThrow(() -> new EntityNotFoundException("MatchRequest ReceiveUpdate Error"));
        if(!matchRequest.getMatchPost().getHomeClub().getId().equals(requestClubId)) {
            throw new NoPermissionException("MatchRequest ReceiveUpdate Error");
        }

        MatchPost matchPost = matchRequest.getMatchPost();
        matchPost.setAwayClub(matchRequest.getSenderClub());
        matchPost.setStartTime(matchRequest.getStartTime());
        matchPost.setEndTime(matchRequest.getEndTime());
        matchPost.setStatus(true);

        matchRequestRepository.deleteByMatchPost(matchPost);

        LocalDate today = LocalDate.now();
        Notification notification = Notification.of("sendYes", today, "", false, matchPost.getAwayClub(), matchRequest.getMatchPost().getHomeClub());
        notificationRepository.save(notification);

        return MatchRequestDto.UpdateResDto.builder().matchRequestId(matchRequest.getId()).build();
    }

    // SendList
    @Transactional(readOnly = true)
    public List<MatchRequestDto.ListResDto> sendList(Long requestClubId){
        Club requestClub = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("MatchRequest SendDashboardList Error"));
        List<MatchRequest> matchRequestList = matchRequestRepository.findBySenderClub(requestClub);

        return matchRequestList.stream().map(MatchRequestDto.ListResDto::toSendListResDto).toList();
    }

    // SendDetail
    @Transactional(readOnly = true)
    public MatchRequestDto.DetailResDto sendDetail(Long matchRequestId){
        MatchRequest matchRequest = matchRequestRepository.findById(matchRequestId).orElseThrow(() -> new EntityNotFoundException("MatchRequest ReceiveDetail Error"));
        return MatchRequestDto.DetailResDto.toSendDetailResDto(matchRequest);
    }

    // SendDelete
    @Transactional
    public MatchRequestDto.DeleteResDto sendDelete(Long matchRequestId, MatchRequestDto.DeleteReqDto deleteReqDto, Long requestClubId){
        MatchRequest matchRequest = matchRequestRepository.findById(matchRequestId).orElseThrow(() -> new EntityNotFoundException("MatchRequest ReceiveDelete Error"));
        if(!matchRequest.getSenderClub().getId().equals(requestClubId)) {
            throw new NoPermissionException("MatchRequest SendDelete Error");
        }

        LocalDate today = LocalDate.now();
        Notification notification = Notification.of("receiveCancel", today, deleteReqDto.getContent(), false, matchRequest.getMatchPost().getHomeClub(), matchRequest.getSenderClub());
        notificationRepository.save(notification);

        matchRequestRepository.deleteById(matchRequestId);
        return MatchRequestDto.DeleteResDto.builder().matchRequestId(matchRequest.getId()).build();
    }
}
