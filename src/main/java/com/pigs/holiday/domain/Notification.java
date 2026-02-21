package com.pigs.holiday.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Notification extends AuditingFields {
    String notiType;
    LocalDate notiDate;
    String content;
    Boolean isRead;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receive_club_id", nullable = false)
    private Club receiveClub;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_club_id", nullable = true)
    private Club awayClub;

    protected Notification(){}
    private Notification(String notiType, LocalDate notiDate, String content, Boolean isRead, Club receiveClub, Club awayClub) {
        this.notiType = notiType;
        this.notiDate = notiDate;
        this.content = content;
        this.isRead = isRead;
        this.receiveClub = receiveClub;
        this.awayClub = awayClub;
    }
    public static Notification of(String notiType, LocalDate notiDate, String content, Boolean isRead, Club receiveClub, Club awayClub) {
        return new Notification(notiType, notiDate, content, isRead, receiveClub, awayClub);
    }
}
