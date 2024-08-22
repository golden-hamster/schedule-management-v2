package com.nbcam.schedule_management_v2.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ScheduleUser {

    @Id @GeneratedValue
    @Column(name = "schedule_user_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Schedule schedule;

    @Builder
    private ScheduleUser(User user, Schedule schedule) {
        this.user = user;
        this.schedule = schedule;
    }
}
