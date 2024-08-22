package com.nbcam.schedule_management_v2.dto.request;

import lombok.Getter;

@Getter
public class UserUpdateRequest {
    private String username;
    private String email;
    private String password;
}
