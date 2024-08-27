package com.nbcam.schedule_management_v2.controller;

import com.nbcam.schedule_management_v2.auth.AuthInfo;
import com.nbcam.schedule_management_v2.auth.Login;
import com.nbcam.schedule_management_v2.dto.request.ScheduleCreateRequest;
import com.nbcam.schedule_management_v2.dto.request.ScheduleUpdateRequest;
import com.nbcam.schedule_management_v2.dto.response.ScheduleListResponse;
import com.nbcam.schedule_management_v2.dto.response.ScheduleResponse;
import com.nbcam.schedule_management_v2.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/api/schedules")
@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<Void> createSchedule(@RequestBody ScheduleCreateRequest scheduleCreateRequest, @Login AuthInfo authInfo) {
        Long scheduleId = scheduleService.createSchedule(scheduleCreateRequest, authInfo);
        return ResponseEntity.created(URI.create("/api/schedules" + scheduleId)).build();
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponse> findSchedule(@PathVariable Long scheduleId) {
        ScheduleResponse scheduleResponse = scheduleService.findScheduleById(scheduleId);
        return ResponseEntity.ok(scheduleResponse);
    }

    @GetMapping
    public ResponseEntity<ScheduleListResponse> findSchedules(
            @PageableDefault(size = 10, sort = "modifiedAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ScheduleResponse> schedules = scheduleService.findSchedules(pageable);
        return ResponseEntity.ok(ScheduleListResponse.builder().scheduleResponses(schedules).build());
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<Void> updateSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleUpdateRequest scheduleUpdateRequest, @Login AuthInfo authInfo) {
        scheduleService.updateSchedule(scheduleId, scheduleUpdateRequest, authInfo);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId, @Login AuthInfo authInfo) {
        scheduleService.deleteSchedule(scheduleId, authInfo);
        return ResponseEntity.noContent().build();
    }
}
