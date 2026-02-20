package com.pigs.holiday.repository;

import com.pigs.holiday.domain.Achievement;
import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.UserAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAchievementRepository extends JpaRepository<UserAchievement, Long> {
    List<UserAchievement> findByClub(Club club);

}
