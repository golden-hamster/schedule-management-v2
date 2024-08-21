package com.nbcam.schedule_management_v2.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CommentListResponse {
    private List<CommentResponse> commentResponses;

    @Builder
    private CommentListResponse(List<CommentResponse> commentResponses) {
        this.commentResponses = commentResponses;
    }
}
