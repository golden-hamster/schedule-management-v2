package com.nbcam.schedule_management_v2;

import com.nbcam.schedule_management_v2.entity.Schedule;
import com.nbcam.schedule_management_v2.entity.ScheduleUser;
import com.nbcam.schedule_management_v2.entity.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final EntityManager em;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void init(){
        for (int i = 1; i <= 10; i++) {
            User user = User.builder()
                    .username("정이삭" + i)
                    .email("isjung" + i + "@gmail.com")
                    .createdAt(LocalDateTime.now())
                    .modifiedAt(LocalDateTime.now())
                    .password("비밀번호입니다.")
                    .build();
            em.persist(user);

            for (int j = 1; j <= 10; j++) {
                Schedule schedule = Schedule.builder()
                        .user(user)
                        .title("일정제목입니다." + i + j)
                        .content("일정내용입니다." + i + j)
                        .createdAt(LocalDateTime.now())
                        .modifiedAt(LocalDateTime.now())
                        .build();
                em.persist(schedule);
                ScheduleUser scheduleUser = ScheduleUser.builder()
                        .schedule(schedule)
                        .user(user)
                        .build();
                em.persist(scheduleUser);
            }
        }
    }
}
