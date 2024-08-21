package com.nbcam.schedule_management_v2.dto.response;

import com.nbcam.schedule_management_v2.entity.Schedule;
import com.nbcam.schedule_management_v2.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Builder(access = AccessLevel.PRIVATE)
@Getter
public class ScheduleResponse {

    private String title;

    private String content;

    private String createdAt;

    private String modifiedAt;

    private String name;

    public static ScheduleResponse from(Schedule schedule) {
        return ScheduleResponse.builder()
                .title(schedule.getTitle())
                .content(schedule.getContent())
                .createdAt(schedule.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분")))
                .modifiedAt(schedule.getModifiedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분")))
                .name(schedule.getUser().getName())
                .build();
    }
}
