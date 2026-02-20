package com.pigs.holiday.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class MatchPost extends AuditingFields {
    String sportCategory;
    LocalDate matchDate;
    String location;
    String locationDetail;
    LocalTime startTime;
    LocalTime endTime;
    String content;
    Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_club_id", nullable = false)
    private Club homeClub;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_club_id", nullable = true)
    private Club awayClub;

    @OneToMany(mappedBy = "matchPost")
    private List<MatchRequest> matchRequestList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "finish_club_id", nullable = true)
    private Club finishClub;


    protected MatchPost() {}
    private MatchPost(String sportCategory, LocalDate matchDate, String location, String locationDetail, LocalTime startTime, LocalTime endTime, String content, Boolean status, Club homeClub, Club awayClub, Club finishClub) {
        this.sportCategory = sportCategory;
        this.matchDate = matchDate;
        this.location = location;
        this.locationDetail = locationDetail;
        this.startTime = startTime;
        this.endTime = endTime;
        this.content = content;
        this.status = status;
        this.homeClub = homeClub;
        this.awayClub = awayClub;
        this.finishClub = finishClub;
    }
    public static MatchPost of(String sportCategory, LocalDate matchDate, String location, String locationDetail, LocalTime startTime, LocalTime endTime, String content, Boolean status, Club homeClub, Club awayClub, Club finishClub) {
        return new MatchPost(sportCategory, matchDate, location, locationDetail, startTime, endTime, content, status, homeClub, awayClub, finishClub);
    }
}
