package com.nbcam.schedule_management_v2.dto.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class CommentListResponse {
    private List<CommentResponse> commentResponses;
}
