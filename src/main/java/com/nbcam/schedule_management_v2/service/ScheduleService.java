package com.nbcam.schedule_management_v2.service;

import com.nbcam.schedule_management_v2.dto.request.ScheduleCreateRequest;
import com.nbcam.schedule_management_v2.dto.request.ScheduleUpdateRequest;
import com.nbcam.schedule_management_v2.dto.response.ScheduleResponse;
import com.nbcam.schedule_management_v2.entity.Schedule;
import com.nbcam.schedule_management_v2.entity.User;
import com.nbcam.schedule_management_v2.repository.ScheduleRepository;
import com.nbcam.schedule_management_v2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long createSchedule(ScheduleCreateRequest scheduleCreateRequest) {
        User user = userRepository.findById(scheduleCreateRequest.getUserId()).orElseThrow(RuntimeException::new);

        Schedule schedule = Schedule.builder()
                .title(scheduleCreateRequest.getTitle())
                .content(scheduleCreateRequest.getContent())
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .user(user)
                .build();

        return scheduleRepository.save(schedule).getId();
    }

    public ScheduleResponse findScheduleById(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(RuntimeException::new);
        return ScheduleResponse.from(schedule);
    }

    public Page<ScheduleResponse> findSchedules(Pageable pageable) {
        return scheduleRepository.findAll(pageable).map(ScheduleResponse::from);
    }

    @Transactional
    public void updateSchedule(Long scheduleId, ScheduleUpdateRequest scheduleUpdateRequest) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(RuntimeException::new);
        User user = userRepository.findById(scheduleUpdateRequest.getUserId()).orElseThrow(RuntimeException::new);
        validateAuthor(schedule, user);
        schedule.updateTitle(scheduleUpdateRequest.getTitle());
        schedule.updateContent(scheduleUpdateRequest.getContent());
        schedule.updateModifiedAt(LocalDateTime.now());
    }

    @Transactional
    public void deleteSchedule(Long scheduleId, Long userId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(RuntimeException::new);
        User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
        validateAuthor(schedule, user);
        scheduleRepository.delete(schedule);
    }

    public void validateAuthor(Schedule schedule, User user) {
        if (!schedule.getUser().equals(user)) {
            throw new RuntimeException();
        }
    }
}
