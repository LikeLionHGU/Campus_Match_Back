package com.pigs.holiday.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class MatchHistory extends AuditingFields {
    LocalDate matchDate;
    String location;
    Boolean matchType;
    String result;
    Boolean isOfficial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_club_id", nullable = false)
    private Club homeClub;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_club_id", nullable = false)
    private Club awayClub;

    protected MatchHistory(){}
    private MatchHistory(LocalDate matchDate, String location, boolean matchType, String result, Boolean isOfficial, Club homeClub, Club awayClub) {
        this.matchDate = matchDate;
        this.location = location;
        this.matchType = matchType;
        this.result = result;
        this.isOfficial = isOfficial;
        this.homeClub = homeClub;
        this.awayClub = awayClub;
    }
    public static MatchHistory of(LocalDate matchDate, String location, boolean matchType, String result, Boolean isOfficial, Club homeClub, Club awayClub) {
        return new MatchHistory(matchDate, location, matchType, result, isOfficial, homeClub, awayClub);
    }
}
