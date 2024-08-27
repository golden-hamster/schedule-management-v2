package com.nbcam.schedule_management_v2.dto.response;

import com.nbcam.schedule_management_v2.entity.Comment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Builder(access = AccessLevel.PRIVATE)
@Getter
public class CommentResponse {
    private Long commentId;
    private String content;
    private String createdAt;
    private String modifiedAt;
    private String username;

    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분")))
                .modifiedAt(comment.getModifiedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분")))
                .username(comment.getUser().getUsername())
                .build();
    }
}
