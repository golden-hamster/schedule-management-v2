package com.nbcam.schedule_management_v2.repository;

import com.nbcam.schedule_management_v2.entity.ScheduleUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleUserRepository extends JpaRepository<ScheduleUser, Long> {

    void deleteByScheduleId(Long scheduleId);

    List<ScheduleUser> findByScheduleId(Long scheduleId);
}
