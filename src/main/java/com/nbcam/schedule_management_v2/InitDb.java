package com.nbcam.schedule_management_v2;

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
        for (int i = 0; i < 3; i++) {
            User user = User.builder()
                    .name("정이삭")
                    .email("isjung7057@gmail.com")
                    .createdAt(LocalDateTime.now())
                    .modifiedAt(LocalDateTime.now())
                    .password("비밀번호입니다.")
                    .build();
            em.persist(user);
        }
    }
}
