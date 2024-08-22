package com.nbcam.schedule_management_v2.dto.response;

import com.nbcam.schedule_management_v2.entity.User;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class Manager {
    private String username;
    private String email;

    public static Manager from(User user) {
        return Manager.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
