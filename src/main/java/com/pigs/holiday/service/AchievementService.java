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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AchievementService {

    private final ClubRepository clubRepository;
    private final AchievementRepository achievementRepository;
    private final UserAchievementRepository userAchievementRepository;

    @Transactional
    public List<AchievementDto.ListResDto> checkAndAssignAchievements(Long clubId) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new EntityNotFoundException("클럽을 찾을 수 없습니다."));

        int postCount = club.getHomeMatchPostList().size();
        int matchCount = club.getTotalMatches();
        double mannerScore = club.getMannerScore();
        int photoCount = club.getGalleryList().size();

        Map<Long, Integer> opponentCounts = new HashMap<>();
        club.getHomeMatchHistoryList().forEach(m -> opponentCounts.merge(m.getAwayClub().getId(), 1, Integer::sum));
        club.getAwayMatchHistoryList().forEach(m -> opponentCounts.merge(m.getHomeClub().getId(), 1, Integer::sum));

        int rematchCount = opponentCounts.values().stream().filter(c -> c > 1).mapToInt(c -> c - 1).sum();
        int uniqueOpponentCount = opponentCounts.size();

        List<Achievement> allAchievements = achievementRepository.findAll(
                Sort.by(Sort.Direction.ASC, "type")
                        .and(Sort.by(Sort.Direction.ASC, "goalCount"))
        );

        List<UserAchievement> myAchievements = userAchievementRepository.findByClub(club);
        Set<Long> myAchievementIds = myAchievements.stream()
                .map(ua -> ua.getAchievement().getId())
                .collect(Collectors.toSet());


        Map<AchievementType, AchievementDto.ListResDto> bestAchievementMap = new LinkedHashMap<>();

        for (Achievement achievement : allAchievements) {
            boolean isAcquired = myAchievementIds.contains(achievement.getId());

            if (!isAcquired) {
                if (checkCondition(achievement, postCount, matchCount, rematchCount, uniqueOpponentCount, mannerScore, photoCount)) {
                    UserAchievement newUa = UserAchievement.of(club, achievement);
                    userAchievementRepository.save(newUa);
                    isAcquired = true;
                    myAchievementIds.add(achievement.getId());
                }
            }

            AchievementDto.ListResDto currentDto = AchievementDto.ListResDto.builder()
                    .id(achievement.getId())
                    .title(achievement.getTitle())
                    .imageUrl(achievement.getImageUrl())
                    .isAcquired(isAcquired)
                    .build();


            AchievementType currentType = achievement.getType();

            if (!bestAchievementMap.containsKey(currentType)) {
                bestAchievementMap.put(currentType, currentDto);
            } else {
                if (isAcquired) {
                    bestAchievementMap.put(currentType, currentDto);
                }
            }

        }

        List<AchievementDto.ListResDto> resultList = new ArrayList<>(bestAchievementMap.values());

        return resultList.stream()
                .filter(AchievementDto.ListResDto::isAcquired)
                .collect(Collectors.toList());
    }


    private boolean checkCondition(Achievement achievement, int postCount, int matchCount, int rematchCount,
                                   int uniqueOpponentCount, double mannerScore, int photoCount) {
        int goal = achievement.getGoalCount();
        return switch (achievement.getType()) {
            case POST_WRITER -> postCount >= goal;
            case MATCH_REQUESTER -> matchCount >= goal;
            case MATCH_PARTNER -> rematchCount >= goal;
            case Ground_Owner -> uniqueOpponentCount >= goal;
            case Gentleman -> mannerScore >= goal;
            case Photogenic -> photoCount >= goal;
        };
    }
}



