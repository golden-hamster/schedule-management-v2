package com.nbcam.schedule_management_v2.dto.request;

import lombok.Getter;

@Getter
public class UserCreateRequest {
    private String username;
    private String email;
    private String password;
    private boolean admin;
    private String adminToken;
}
