package com.nbcam.schedule_management_v2.repository;

import com.nbcam.schedule_management_v2.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByScheduleId(Long scheduleId);
}
