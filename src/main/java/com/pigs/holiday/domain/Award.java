package com.pigs.holiday.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Award extends AuditingFields {
    String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    protected Award(){}
    private Award(String title, Club club) {
        this.title = title;
        this.club = club;
    }
    public static Award of(String title, Club club) {
        return new Award(title, club);
    }
}
