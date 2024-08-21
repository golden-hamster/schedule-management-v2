package com.nbcam.schedule_management_v2.dto.response;

import lombok.*;
import org.springframework.data.domain.Page;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class ScheduleListResponse {
    private Page<ScheduleResponse> scheduleResponses;
}
