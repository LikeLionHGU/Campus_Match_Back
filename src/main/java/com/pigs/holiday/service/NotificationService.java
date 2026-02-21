package com.pigs.holiday.service;

import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.Notification;
import com.pigs.holiday.dto.NotificationDto;
import com.pigs.holiday.repository.ClubRepository;
import com.pigs.holiday.repository.NotificationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NotificationService {

    final NotificationRepository notificationRepository;
    final ClubRepository clubRepository;


    // Check
    public NotificationDto.CheckResDto check(Long requestClubId) {
        Club club = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("Notification Check Error"));

        LocalDate today = LocalDate.now();

        Boolean isNew = notificationRepository.existsByReceiveClubAndIsReadAndNotiDateLessThanEqual(club, false, today);

        return NotificationDto.CheckResDto.toCheckResDto(isNew);
    }


    // DefaultList
    @Transactional
    public NotificationDto.ListResDto defaultList(Long requestClubId) {
        Club club = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("Notification DefaultList Error"));

        LocalDate today = LocalDate.now();

        NotificationDto.ListResDto listResDto = new NotificationDto.ListResDto();
        listResDto.setDefaultNoti(notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "rematch", today)
                + notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "remind", today)
                + notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "schedule", today)
                + notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "matchCancel", today));

        listResDto.setSendNoti(notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "sendYes", today)
                + notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "sendNo", today));

        listResDto.setReceiveNoti(notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "receive", today)
                + notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "receiveCancel", today));

        listResDto.setFinishNoti(notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "finish", today));

        listResDto.setClubId(club.getId());

        List<Notification> detailList = new ArrayList<>();
        detailList.addAll(notificationRepository.findByReceiveClubAndNotiTypeAndNotiDateLessThanEqualOrderByIdDesc(club, "rematch", today));
        detailList.addAll(notificationRepository.findByReceiveClubAndNotiTypeAndNotiDateLessThanEqualOrderByIdDesc(club, "remind", today));
        detailList.addAll(notificationRepository.findByReceiveClubAndNotiTypeAndNotiDateLessThanEqualOrderByIdDesc(club, "schedule", today));
        detailList.addAll(notificationRepository.findByReceiveClubAndNotiTypeAndNotiDateLessThanEqualOrderByIdDesc(club, "matchCancel", today));

        for (Notification notification : detailList) {
            notification.setIsRead(true);
        }

        List<NotificationDto.DetailResDto> detailResDtoList = new ArrayList<>();
        detailResDtoList.addAll(notificationRepository.findByReceiveClubAndNotiTypeAndNotiDateLessThanEqualOrderByIdDesc(club, "rematch", today).stream().map(NotificationDto.DetailResDto::toRematchDetailResDto).toList());
        detailResDtoList.addAll(notificationRepository.findByReceiveClubAndNotiTypeAndNotiDateLessThanEqualOrderByIdDesc(club, "remind", today).stream().map(NotificationDto.DetailResDto::toRemindDetailResDto).toList());
        detailResDtoList.addAll(notificationRepository.findByReceiveClubAndNotiTypeAndNotiDateLessThanEqualOrderByIdDesc(club, "schedule", today).stream().map(NotificationDto.DetailResDto::toScheduleDetailResDto).toList());
        detailResDtoList.addAll(notificationRepository.findByReceiveClubAndNotiTypeAndNotiDateLessThanEqualOrderByIdDesc(club, "matchCancel", today).stream().map(NotificationDto.DetailResDto::toMatchCancelDetailResDto).toList());

        listResDto.setDetailResDtoList(detailResDtoList);

        return listResDto;
    }

    // SendList
    @Transactional
    public NotificationDto.ListResDto sendList(Long requestClubId) {
        Club club = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("Notification SendList Error"));

        LocalDate today = LocalDate.now();

        NotificationDto.ListResDto listResDto = new NotificationDto.ListResDto();
        listResDto.setDefaultNoti(notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "rematch", today)
                + notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "remind", today)
                + notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "schedule", today)
                + notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "matchCancel", today));

        listResDto.setSendNoti(notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "sendYes", today)
                + notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "sendNo", today));

        listResDto.setReceiveNoti(notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "receive", today)
                + notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "receiveCancel", today));

        listResDto.setFinishNoti(notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "finish", today));

        listResDto.setClubId(club.getId());

        List<Notification> detailList = new ArrayList<>();
        detailList.addAll(notificationRepository.findByReceiveClubAndNotiTypeAndNotiDateLessThanEqualOrderByIdDesc(club, "sendYes", today));
        detailList.addAll(notificationRepository.findByReceiveClubAndNotiTypeAndNotiDateLessThanEqualOrderByIdDesc(club, "sendNo", today));

        for (Notification notification : detailList) {
            notification.setIsRead(true);
        }

        List<NotificationDto.DetailResDto> detailResDtoList = new ArrayList<>();
        detailResDtoList.addAll(notificationRepository.findByReceiveClubAndNotiTypeAndNotiDateLessThanEqualOrderByIdDesc(club, "sendYes", today).stream().map(NotificationDto.DetailResDto::toSendYesDetailResDto).toList());
        detailResDtoList.addAll(notificationRepository.findByReceiveClubAndNotiTypeAndNotiDateLessThanEqualOrderByIdDesc(club, "sendNo", today).stream().map(NotificationDto.DetailResDto::toSendNoDetailResDto).toList());

        listResDto.setDetailResDtoList(detailResDtoList);

        return listResDto;
    }

    // ReceiveList
    @Transactional
    public NotificationDto.ListResDto receiveList(Long requestClubId) {
        Club club = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("Notification ReceiveList Error"));

        LocalDate today = LocalDate.now();

        NotificationDto.ListResDto listResDto = new NotificationDto.ListResDto();
        listResDto.setDefaultNoti(notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "rematch", today)
                + notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "remind", today)
                + notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "schedule", today)
                + notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "matchCancel", today));

        listResDto.setSendNoti(notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "sendYes", today)
                + notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "sendNo", today));

        listResDto.setReceiveNoti(notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "receive", today)
                + notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "receiveCancel", today));

        listResDto.setFinishNoti(notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "finish", today));

        listResDto.setClubId(club.getId());

        List<Notification> detailList = new ArrayList<>();
        detailList.addAll(notificationRepository.findByReceiveClubAndNotiTypeAndNotiDateLessThanEqualOrderByIdDesc(club, "receive", today));
        detailList.addAll(notificationRepository.findByReceiveClubAndNotiTypeAndNotiDateLessThanEqualOrderByIdDesc(club, "receiveCancel", today));

        for (Notification notification : detailList) {
            notification.setIsRead(true);
        }

        List<NotificationDto.DetailResDto> detailResDtoList = new ArrayList<>();
        detailResDtoList.addAll(notificationRepository.findByReceiveClubAndNotiTypeAndNotiDateLessThanEqualOrderByIdDesc(club, "receive", today).stream().map(NotificationDto.DetailResDto::toReceiveDetailResDto).toList());
        detailResDtoList.addAll(notificationRepository.findByReceiveClubAndNotiTypeAndNotiDateLessThanEqualOrderByIdDesc(club, "receiveCancel", today).stream().map(NotificationDto.DetailResDto::toReceiveCancelDetailResDto).toList());

        listResDto.setDetailResDtoList(detailResDtoList);

        return listResDto;
    }

    // FinishList
    @Transactional
    public NotificationDto.ListResDto finishList(Long requestClubId) {
        Club club = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("Notification FinishList Error"));

        LocalDate today = LocalDate.now();

        NotificationDto.ListResDto listResDto = new NotificationDto.ListResDto();
        listResDto.setDefaultNoti(notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "rematch", today)
                + notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "remind", today)
                + notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "schedule", today)
                + notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "matchCancel", today));

        listResDto.setSendNoti(notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "sendYes", today)
                + notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "sendNo", today));

        listResDto.setReceiveNoti(notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "receive", today)
                + notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "receiveCancel", today));

        listResDto.setFinishNoti(notificationRepository.countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(club, false, "finish", today));

        listResDto.setClubId(club.getId());

        List<Notification> detailList = new ArrayList<>();
        detailList.addAll(notificationRepository.findByReceiveClubAndNotiTypeAndNotiDateLessThanEqualOrderByIdDesc(club, "finish", today));

        for (Notification notification : detailList) {
            notification.setIsRead(true);
        }

        List<NotificationDto.DetailResDto> detailResDtoList = new ArrayList<>();
        detailResDtoList.addAll(notificationRepository.findByReceiveClubAndNotiTypeAndNotiDateLessThanEqualOrderByIdDesc(club, "finish", today).stream().map(NotificationDto.DetailResDto::toFinishDetailResDto).toList());

        listResDto.setDetailResDtoList(detailResDtoList);

        return listResDto;
    }

    // Delete
    public NotificationDto.DeleteResDto delete(Long notificationId){
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(() -> new EntityNotFoundException("Notification Delete Error"));
        notificationRepository.deleteById(notificationId);

        return NotificationDto.DeleteResDto.toDeleteResDto(notification);
    }
}
