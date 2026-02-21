package com.pigs.holiday.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import com.pigs.holiday.dto.ClubDto;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Club extends AuditingFields {
    String username;
    String password;
    String name;
    String university;
    String phone;
    String email;
    String clubName;
    String description;
    String region;
    String sportCategory;
    String imageUrl;
    int totalMatches;
    int totalWins;
    int totalDraws;
    int totalLosses;
    double mannerScore;
    LocalDate dashboardDate;

    @OneToMany(mappedBy = "homeClub")
    private List<MatchHistory> homeMatchHistoryList = new ArrayList<>();

    @OneToMany(mappedBy = "awayClub")
    private List<MatchHistory> awayMatchHistoryList = new ArrayList<>();

    @OneToMany(mappedBy = "club")
    private List<Gallery> galleryList = new ArrayList<>();

    @OneToMany(mappedBy = "club")
    private List<Schedule> scheduleList = new ArrayList<>();

    @OneToMany(mappedBy = "homeClub")
    private List<MatchPost> homeMatchPostList = new ArrayList<>();

    @OneToMany(mappedBy = "awayClub")
    private List<MatchPost> awayMatchPostList = new ArrayList<>();

    @OneToMany(mappedBy = "finishClub")
    private List<MatchPost> finishMatchPostList = new ArrayList<>();

    @OneToMany(mappedBy = "senderClub")
    private List<MatchRequest> matchRequestList = new ArrayList<>();

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserAchievement> userAchievements = new ArrayList<>();

    @OneToMany(mappedBy = "receiveClub")
    private List<Notification> notificationList = new ArrayList<>();

    @OneToMany(mappedBy = "awayClub")
    private List<Notification> awaynotificationList = new ArrayList<>();

    @OneToMany(mappedBy = "club")
    private List<Award> awardList = new ArrayList<>();

    protected Club(){}
    private Club(String username, String password, String name, String university, String phone, String email, String clubName, String description, String region, String sportCategory, String imageUrl, int totalMatches, int totalWins, int totalDraws, int totalLosses, double mannerScore, LocalDate dashboardDate) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.university = university;
        this.phone = phone;
        this.email = email;
        this.clubName = clubName;
        this.description = description;
        this.region = region;
        this.sportCategory = sportCategory;
        this.imageUrl = imageUrl;
        this.totalMatches = totalMatches;
        this.totalWins = totalWins;
        this.totalDraws = totalDraws;
        this.totalLosses = totalLosses;
        this.mannerScore = mannerScore;
        this.dashboardDate = dashboardDate;
    }
    public static Club of(String username, String password, String name, String university, String phone, String email, String clubName, String description, String region, String sportCategory, String imageUrl, int totalMatches, int totalWins, int totalDraws, int totalLosses, double mannerScore, LocalDate dashboardDate) {
        return new Club(username, password,  name, university, phone, email, clubName, description, region, sportCategory, imageUrl, totalMatches, totalWins, totalDraws, totalLosses, mannerScore, dashboardDate);
    }





    public ClubDto.SignupResDto toSignupResDto() { return ClubDto.SignupResDto.builder().id(getId()).build(); }
}
