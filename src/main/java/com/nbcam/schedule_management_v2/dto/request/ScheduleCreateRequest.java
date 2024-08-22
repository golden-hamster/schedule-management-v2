package com.nbcam.schedule_management_v2.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class ScheduleCreateRequest {
    private String title;
    private String content;
    private Long userId;
    private List<Long> managerIdList;
}
