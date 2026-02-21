package com.pigs.holiday.repository;

import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {
    List<Gallery> findByIdAndDeletedAndIsOfficial(Long id, Boolean deleted, Boolean isOfficial);

    List<Gallery> findByClubAndDeletedAndIsOfficial(Club club, Boolean deleted, Boolean isOfficial);
}
