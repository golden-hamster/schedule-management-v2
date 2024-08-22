package com.nbcam.schedule_management_v2.dto.request;

import lombok.Getter;

@Getter
public class CommentCreateRequest {
    private String content;
    private Long userId;
}
