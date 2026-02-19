package com.pigs.holiday.repository;

import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    int countByReceiveClubAndIsReadAndNotiTypeAndNotiDateLessThanEqual(Club receiveClub, Boolean isRead, String notiType, LocalDate notiDateIsLessThan);
    List<Notification> findByReceiveClubAndNotiTypeAndNotiDateLessThanEqualOrderByIdDesc(Club receiveClub, String notiType, LocalDate notiDateIsLessThan);
    Boolean existsByReceiveClubAndIsReadAndNotiDateLessThanEqual(Club receiveClub, Boolean isRead, LocalDate notiDateIsLessThan);
}
