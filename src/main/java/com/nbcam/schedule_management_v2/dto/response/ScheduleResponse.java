package com.nbcam.schedule_management_v2.dto.response;

import com.nbcam.schedule_management_v2.entity.Schedule;
import com.nbcam.schedule_management_v2.entity.User;
import lombok.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class ScheduleResponse {

    private Long scheduleId;

    private String title;

    private String content;

    private String createdAt;

    private String modifiedAt;

    private String username;

    private String email;

    private List<Manager> managers;

    public static ScheduleResponse from(Schedule schedule, List<User> managers) { // 단건 조회
        return ScheduleResponse.builder()
                .scheduleId(schedule.getId())
                .title(schedule.getTitle())
                .content(schedule.getContent())
                .createdAt(schedule.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분")))
                .modifiedAt(schedule.getModifiedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분")))
                .username(schedule.getUser().getUsername())
                .email(schedule.getUser().getEmail())
                .managers(managers.stream().map(Manager::from).toList())
                .build();
    }

    public static ScheduleResponse from(Schedule schedule) { // 여러 건 조회
        return ScheduleResponse.builder()
                .scheduleId(schedule.getId())
                .title(schedule.getTitle())
                .content(schedule.getContent())
                .createdAt(schedule.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분")))
                .modifiedAt(schedule.getModifiedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분")))
                .build();
    }
}
