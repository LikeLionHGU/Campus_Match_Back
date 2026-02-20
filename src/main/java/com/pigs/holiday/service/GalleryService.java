package com.pigs.holiday.service;

import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.Gallery;
import com.pigs.holiday.domain.GalleryImage;
import com.pigs.holiday.dto.GalleryDto;
import com.pigs.holiday.exception.NoPermissionException;
import com.pigs.holiday.mapper.GalleryMapper;
import com.pigs.holiday.repository.ClubRepository;
import com.pigs.holiday.repository.GalleryImageRepository;
import com.pigs.holiday.repository.GalleryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GalleryService {

    final ClubRepository clubRepository;
    final GalleryRepository galleryRepository;
    final GalleryImageRepository galleryImageRepository;

    final GalleryMapper galleryMapper;

    // Create
    public GalleryDto.CreateResDto create(GalleryDto.CreateReqDto createReqDto, List<String> imageUrls, Long requestClubId) {
        Club club = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("Gallery Create Error"));
        Gallery gallery = createReqDto.toEntity(club);

        galleryRepository.save(gallery);

        if (imageUrls != null) {
            for (String url : imageUrls) {
                GalleryImage galleryImage = GalleryImage.of(url, gallery);

                galleryImageRepository.save(galleryImage);
            }
        }

        return GalleryDto.CreateResDto.toCreateResDto(gallery);
    }

    // Create
    public GalleryDto.CreateResDto finish(GalleryDto.CreateReqDto createReqDto, List<String> imageUrls, Long requestClubId) {
        Club club = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("Gallery Create Error"));
        Gallery gallery = createReqDto.toFinish(club);

        galleryRepository.save(gallery);

        if (imageUrls != null) {
            for (String url : imageUrls) {
                GalleryImage galleryImage = GalleryImage.of(url, gallery);

                galleryImageRepository.save(galleryImage);
            }
        }

        return GalleryDto.CreateResDto.toCreateResDto(gallery);
    }


    // List
    @Transactional(readOnly = true)
    public List<GalleryDto.ListResDto> list(Long clubId, GalleryDto.ListReqDto listReqDto) {
        return galleryMapper.list(clubId, listReqDto);
    }

    // MatchList
    @Transactional(readOnly = true)
    public List<GalleryDto.ListResDto> matchList(Long clubId, GalleryDto.ListReqDto listReqDto) {
        return galleryMapper.matchList(clubId, listReqDto);
    }

    // MyClubList
    @Transactional(readOnly = true)
    public List<GalleryDto.ListResDto> myClubList(Long clubId, GalleryDto.ListReqDto listReqDto) {
        return galleryMapper.myClubList(clubId, listReqDto);
    }

    // Detail
    @Transactional(readOnly = true)
    public GalleryDto.DetailResDto detail(Long galleryId, Long requestClubId) {
        Gallery gallery = galleryRepository.findById(galleryId).orElseThrow(() -> new EntityNotFoundException("Gallery Detail Error"));

        return GalleryDto.DetailResDto.toDetailResDto(gallery, gallery.getClub().getId().equals(requestClubId));
    }

    // Update
    @Transactional
    public GalleryDto.UpdateResDto update(Long galleryId, GalleryDto.UpdateReqDto updateReqDto, List<String> imageUrls, Long requestClubId) {
        Gallery gallery = galleryRepository.findById(galleryId).orElseThrow(() -> new EntityNotFoundException("Gallery Update Error"));

        if(!gallery.getClub().getId().equals(requestClubId)) {
            throw new NoPermissionException("Gallery Update Error");
        }

        if(StringUtils.hasText(updateReqDto.getTitle()) && !updateReqDto.getTitle().equals(gallery.getTitle())){
            gallery.setTitle(updateReqDto.getTitle());
        }
        if(updateReqDto.getMatchDate()!=null && !updateReqDto.getMatchDate().equals(gallery.getMatchDate())){
            gallery.setMatchDate(updateReqDto.getMatchDate());
        }

        if (imageUrls != null && !imageUrls.isEmpty()) {
            List<GalleryImage> currentImages = gallery.getGalleryImageList();

            List<GalleryImage> imagesToDelete = currentImages.stream()
                    .filter(img -> !imageUrls.contains(img.getImageUrl()))
                    .toList();

            galleryImageRepository.deleteAll(imagesToDelete);
            currentImages.removeAll(imagesToDelete);

            for (String url : imageUrls) {
                boolean isExist = currentImages.stream()
                        .anyMatch(img -> img.getImageUrl().equals(url));

                if (!isExist) {
                    GalleryImage newImage = GalleryImage.of(url, gallery);
                    galleryImageRepository.save(newImage);
                    currentImages.add(newImage);
                }
            }
        }

        return GalleryDto.UpdateResDto.toUpdateResDto(gallery);
    }

    // Delete
    @Transactional
    public GalleryDto.DeleteResDto delete(Long galleryId, Long requestClubId) {
        Gallery gallery = galleryRepository.findById(galleryId).orElseThrow(() -> new EntityNotFoundException("Gallery Delete Error"));

        if(!gallery.getClub().getId().equals(requestClubId)) {
            throw new NoPermissionException("Gallery Delete Error");
        }

        gallery.setDeleted(true);

        return GalleryDto.DeleteResDto.toDeleteResDto(gallery);
    }

    // Gallery Dashboard
    public List<GalleryDto.DashboardResDto> galleryDashboard(Long clubId) {
        List<Gallery> galleryList = galleryRepository.findByIdAndDeletedAndIsOfficial(clubId, false, true);

        return galleryList.stream().map(GalleryDto.DashboardResDto::toDashboardResDto).toList();
    }
}
