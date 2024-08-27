package com.nbcam.schedule_management_v2.auth;

import com.nbcam.schedule_management_v2.entity.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthInfo {
    private final Long userId;
    private final String email;
    private final Role role;
}
