package com.nbcam.schedule_management_v2.dto.request;

import lombok.Getter;

@Getter
public class ScheduleCreateRequest {
    private String title;
    private String content;
    private Long userId;
}
