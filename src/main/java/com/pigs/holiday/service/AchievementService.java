package com.pigs.holiday.service;

import com.pigs.holiday.domain.Achievement;
import com.pigs.holiday.domain.AchievementType;
import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.UserAchievement;
import com.pigs.holiday.dto.AchievementDto;
import com.pigs.holiday.repository.AchievementRepository;
import com.pigs.holiday.repository.ClubRepository;
import com.pigs.holiday.repository.UserAchievementRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AchievementService {

    private final ClubRepository clubRepository;
    private final AchievementRepository achievementRepository;
    private final UserAchievementRepository userAchievementRepository;

    public List<AchievementDto.ListResDto> checkAndAssignAchievements(Long clubId) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new EntityNotFoundException("클럽을 찾을 수 없습니다."));


        int[] commonGoals = {1, 5, 10};

        List<AchievementDto.ListResDto> newAchievements = new ArrayList<>();

        //POST_WRITER
        int postCount = club.getHomeMatchPostList().size() + club.getAwayMatchPostList().size();

        assignAchievementIfEligible(club, AchievementType.POST_WRITER, postCount, commonGoals, newAchievements);

        //MATCH_REQUESTER
        int matchCount = club.getTotalMatches();
        assignAchievementIfEligible(club, AchievementType.MATCH_REQUESTER, matchCount, commonGoals, newAchievements);

        //MATCH_PARTNER
        java.util.Map<Long, Integer> opponentCounts = new java.util.HashMap<>();

        club.getHomeMatchHistoryList().forEach(match -> {
            Long opponentId = match.getAwayClub().getId();
            opponentCounts.put(opponentId, opponentCounts.getOrDefault(opponentId, 0) + 1);
        });
        club.getAwayMatchHistoryList().forEach(match -> {
            Long opponentId = match.getHomeClub().getId();
            opponentCounts.put(opponentId, opponentCounts.getOrDefault(opponentId, 0) + 1);
        });

        int rematchCount = opponentCounts.values().stream()
                .filter(count -> count > 1)
                .mapToInt(count -> count - 1)
                .sum();
        assignAchievementIfEligible(club, AchievementType.MATCH_PARTNER, rematchCount, commonGoals, newAchievements);


        //Ground_Owner
        int exchangeCount = opponentCounts.size();
        assignAchievementIfEligible(club, AchievementType.Ground_Owner, exchangeCount, commonGoals, newAchievements);

        //Gentleman
        int[] mannerGoals = {40, 50, 60};
        double mannerScore = club.getMannerScore();
        assignAchievementIfEligible(club, AchievementType.Gentleman, mannerScore, mannerGoals, newAchievements);

        //Photogenic
        int photoCount = club.getGalleryList().size();
        assignAchievementIfEligible(club, AchievementType.Photogenic, photoCount, commonGoals, newAchievements);

        return newAchievements;
    }

    private void assignAchievementIfEligible(Club club, AchievementType type, double currentCount, int[] goals, List<AchievementDto.ListResDto> unlockedList) {

        for (int goal : goals) {
            if (currentCount >= goal) {

                Achievement targetAchievement = achievementRepository.findByTypeAndGoalCount(type, goal)
                        .orElse(null);

                if (targetAchievement != null) {

                    boolean alreadyHas = userAchievementRepository.existsByClubAndAchievement(club, targetAchievement);
                    if (!alreadyHas) {
                        UserAchievement newAchievement = UserAchievement.of(club, targetAchievement);
                        userAchievementRepository.save(newAchievement);
                        unlockedList.add(AchievementDto.ListResDto.from(targetAchievement));
                    }
                }
            }
        }
    }


}
