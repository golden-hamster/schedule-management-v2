package com.nbcam.schedule_management_v2.repository;

import com.nbcam.schedule_management_v2.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
