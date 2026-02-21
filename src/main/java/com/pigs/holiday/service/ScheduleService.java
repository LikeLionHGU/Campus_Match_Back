package com.pigs.holiday.service;

import com.pigs.holiday.domain.Club;
import com.pigs.holiday.domain.Notification;
import com.pigs.holiday.domain.Schedule;
import com.pigs.holiday.dto.MatchPostDto;
import com.pigs.holiday.dto.ScheduleDto;
import com.pigs.holiday.exception.NoPermissionException;
import com.pigs.holiday.repository.ClubRepository;
import com.pigs.holiday.repository.NotificationRepository;
import com.pigs.holiday.repository.ScheduleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class ScheduleService {

    final ScheduleRepository scheduleRepository;
    final ClubRepository clubRepository;
    final NotificationRepository notificationRepository;

    final MatchPostService matchPostService;

    public ScheduleDto.CreateResDto create(ScheduleDto.CreateReqDto createReqDto, Long requestClubId) {
        Club requestClub = clubRepository.findById(requestClubId).orElseThrow(() -> new EntityNotFoundException("schedule Create Error"));
        Schedule schedule = createReqDto.toEntity(requestClub);
        schedule.setClub(requestClub);

        return ScheduleDto.CreateResDto.toCreateResDto(scheduleRepository.save(schedule));
    }

    // List
    @Transactional(readOnly = true)
    public ScheduleDto.CalendarResDto list(Long clubId, Long requestClubId) {
        Club requestClub = clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("schedule List Error"));

        ScheduleDto.CalendarResDto calendarResDto = ScheduleDto.CalendarResDto.toCalendarResDto(clubId.equals(requestClubId));

        calendarResDto.setScheduleResDtoList(scheduleRepository.findByClubIdAndDeleted(clubId, false).stream().map(ScheduleDto.ScheduleResDto::toScheduleResDto).toList());
        calendarResDto.setUpcomingResDtoList(matchPostService.upcomingDashboard(clubId));
        calendarResDto.setOngoingResDtoList(matchPostService.ongoingDashboard(clubId));
        calendarResDto.setMatchResDtoList(matchPostService.matchPostDashboard(clubId));
        calendarResDto.setPastResDtoList(matchPostService.pastDashboard(clubId));

        return calendarResDto;
    }

    @Transactional(readOnly = true)
    public ScheduleDto.DetailResDto detail(Long ScheduleId) {
        Schedule schedule = scheduleRepository.findById(ScheduleId).orElseThrow(() -> new EntityNotFoundException("schedule Detail Error"));
        return ScheduleDto.DetailResDto.toDetailResDto(schedule);
    }

    @Transactional
    public ScheduleDto.UpdateResDto update(ScheduleDto.UpdateReqDto reqDto, Long scheduleId, Long requestClubId) {

        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("Schedule Update Error"));

        if (!schedule.getClub().getId().equals(requestClubId)) {
            throw new NoPermissionException("Schedule Update Error");
        }

        if (StringUtils.hasText(reqDto.getTitle()) && !reqDto.getTitle().equals(schedule.getTitle())) {
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

        return ScheduleDto.UpdateResDto.toUpdateResDto(schedule);
    }

    @Transactional
    public ScheduleDto.DeleteResDto delete(Long scheduleId, Long requestClubId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new EntityNotFoundException("delete Error"));

        if(!schedule.getClub().getId().equals(requestClubId)) {
            throw new NoPermissionException("Schedule Delete Error");
        }

        scheduleRepository.delete(schedule);
        return ScheduleDto.DeleteResDto.toDeleteResDto(schedule);
    }

    // ScheduleDashboard
    @Transactional(readOnly = true)
    public List<ScheduleDto.DashboardResDto> scheduleDashboard(Long clubId) {
        List<Schedule> schedulesList = scheduleRepository.findByClubIdAndDeleted(clubId, false);

        return schedulesList.stream().map(ScheduleDto.DashboardResDto::toDashboardResDto).toList();
    }
}
