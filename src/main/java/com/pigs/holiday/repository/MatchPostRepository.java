package com.pigs.holiday.repository;

import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.MatchPost;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MatchPostRepository extends JpaRepository<MatchPost, Long> {
    List<MatchPost> findByHomeClubAndStatusAndDeleted(Club homeClub, Boolean status, Boolean deleted);
    List<MatchPost> findByStatusAndDeletedAndMatchDateLessThan(Boolean status, Boolean deleted, LocalDate targetDate);

    List<MatchPost> findByHomeClubAndDeletedAndStatusAndMatchDate(Club homeClub, Boolean Deleted, Boolean status, LocalDate matchDate);
    List<MatchPost> findByAwayClubAndDeletedAndStatusAndMatchDate(Club awayClub, Boolean Deleted, Boolean status, LocalDate matchDate);

    List<MatchPost> findByHomeClubAndDeletedAndStatusAndMatchDateGreaterThanOrderByMatchDateDesc(Club homeClub, Boolean deleted, Boolean status, LocalDate matchDateIsGreaterThan);
    List<MatchPost> findByAwayClubAndDeletedAndStatusAndMatchDateGreaterThanOrderByMatchDateDesc(Club awayClub, Boolean deleted, Boolean status, LocalDate matchDateIsGreaterThan);

    List<MatchPost> findByHomeClubAndDeletedAndStatusAndMatchDateLessThanOrderByMatchDateDesc(Club homeClub, Boolean deleted, Boolean status, LocalDate matchDateIsLessThan);
    List<MatchPost> findByAwayClubAndDeletedAndStatusAndMatchDateLessThanOrderByMatchDateDesc(Club awayClub, Boolean deleted, Boolean status, LocalDate matchDateIsLessThan);

    @Query("""
    select matchPost from MatchPost matchPost
    where matchPost.homeClub = :club
      and matchPost.status = true
      and matchPost.deleted = true
      and (matchPost.finishClub is null or matchPost.finishClub <> :club)
    """)
    List<MatchPost> findFinishHomeList(@Param("club") Club club);

    @Query("""
    select matchPost from MatchPost matchPost
    where matchPost.awayClub = :club
      and matchPost.status = true
      and matchPost.deleted = true
      and (matchPost.finishClub is null or matchPost.finishClub <> :club)
    """)
    List<MatchPost> findFinishAwayList(@Param("club") Club club);
}
