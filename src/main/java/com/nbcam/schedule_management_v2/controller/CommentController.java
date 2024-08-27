package com.nbcam.schedule_management_v2.controller;

import com.nbcam.schedule_management_v2.auth.AuthInfo;
import com.nbcam.schedule_management_v2.auth.Login;
import com.nbcam.schedule_management_v2.dto.request.CommentCreateRequest;
import com.nbcam.schedule_management_v2.dto.request.CommentUpdateRequest;
import com.nbcam.schedule_management_v2.dto.response.CommentListResponse;
import com.nbcam.schedule_management_v2.dto.response.CommentResponse;
import com.nbcam.schedule_management_v2.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<Void> createComment(@PathVariable Long scheduleId, @RequestBody CommentCreateRequest commentCreateRequest, @Login AuthInfo authInfo) {
        Long commentId = commentService.saveComment(scheduleId, commentCreateRequest, authInfo);
        return ResponseEntity.created(URI.create("/api/comments/" + commentId)).build();
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponse> findComment(@PathVariable Long commentId) {
        CommentResponse commentResponse = commentService.findCommentById(commentId);
        return ResponseEntity.ok(commentResponse);
    }

    @GetMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CommentListResponse> findComments(@PathVariable Long scheduleId) {
        List<CommentResponse> comments = commentService.findComments(scheduleId);
        return ResponseEntity.ok(CommentListResponse.builder().commentResponses(comments).build());
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<Void> updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest commentUpdateRequest, @Login AuthInfo authInfo) {
        commentService.updateComment(commentId, commentUpdateRequest, authInfo);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId, @Login AuthInfo authInfo) {
        commentService.deleteComment(commentId, authInfo);
        return ResponseEntity.noContent().build();
    }
}
