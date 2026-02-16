package com.pigs.holiday.service;

import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.Notification;
import com.pigs.holiday.domain.Schedule;
import com.pigs.holiday.dto.ScheduleDto;
import com.pigs.holiday.exception.NoPermissionException;
import com.pigs.holiday.repository.ClubRepository;
import com.pigs.holiday.repository.NotificationRepository;
import com.pigs.holiday.repository.ScheduleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class ScheduleService {

    final ScheduleRepository scheduleRepository;
    final ClubRepository clubRepository;
    final NotificationRepository notificationRepository;

    public ScheduleDto.CreateResDto create(ScheduleDto.CreateReqDto createReqDto, Long clubId){
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("schedule Create Error"));
        Schedule schedule = createReqDto.toEntity(club);
        schedule.setClub(club);

//        LocalDate today = LocalDate.now();
//        Notification notification = Notification.of("schedule", today, schedule.getTitle(), false, club, null);
//        notificationRepository.save(notification);

        return ScheduleDto.CreateResDto.toCreateResDto(scheduleRepository.save(schedule));
    }

    //여기 고쳐야함 씨부럴
    @Transactional(readOnly = true)
    public List<ScheduleDto.ListResDto> list(Long clubId) {
        List<Schedule> schedulesList = scheduleRepository.findByClubIdAndDeleted(clubId, false);

        return schedulesList.stream()
                .map(ScheduleDto.ListResDto::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ScheduleDto.DetailResDto detail(Long ScheduleId, Long clubId){
        Schedule schedule = scheduleRepository.findById(ScheduleId).orElseThrow(() -> new EntityNotFoundException("schedule Detail Error"));
        ScheduleDto.DetailResDto detailResDto = ScheduleDto.DetailResDto.toDetailResDto(schedule);
        detailResDto.setMyClub(clubId.equals(schedule.getClub().getId()));
        return detailResDto;
    }

    @Transactional
    public ScheduleDto.UpdateResDto update(ScheduleDto.UpdateReqDto reqDto, Long scheduleId, Long requestClubId) { // reqDto 필수!

        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("Schedule Update Error"));

        if (!schedule.getClub().getId().equals(requestClubId)) {
            throw new NoPermissionException("Schedule Update Error");
        }

        if (reqDto.getTitle() != null && !reqDto.getTitle().isBlank()) {
            schedule.setTitle(reqDto.getTitle());
        }
        if (reqDto.getStartDate() != null) {
            schedule.setStartDate(reqDto.getStartDate());
        }
        if(reqDto.getEndDate() != null) {
            schedule.setEndDate(reqDto.getEndDate());
        }
        if (reqDto.getStartTime() != null) {
            schedule.setStartTime(reqDto.getStartTime());
        }
        if(reqDto.getEndTime() != null) {
            schedule.setEndTime(reqDto.getEndTime());
        }

        return ScheduleDto.UpdateResDto.builder().reqId(scheduleId).build();
    }

    @Transactional
    public ScheduleDto.DeleteResDto delete(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new EntityNotFoundException("delete Error"));
        scheduleRepository.delete(schedule);
        return ScheduleDto.DeleteResDto.builder().reqId(scheduleId).build();
    }
}
