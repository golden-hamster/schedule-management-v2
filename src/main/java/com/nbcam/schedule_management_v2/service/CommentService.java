package com.nbcam.schedule_management_v2.service;

import com.nbcam.schedule_management_v2.dto.request.CommentCreateRequest;
import com.nbcam.schedule_management_v2.dto.request.CommentUpdateRequest;
import com.nbcam.schedule_management_v2.dto.response.CommentResponse;
import com.nbcam.schedule_management_v2.entity.Comment;
import com.nbcam.schedule_management_v2.entity.Schedule;
import com.nbcam.schedule_management_v2.entity.User;
import com.nbcam.schedule_management_v2.repository.CommentRepository;
import com.nbcam.schedule_management_v2.repository.ScheduleRepository;
import com.nbcam.schedule_management_v2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long saveComment(Long scheduleId,CommentCreateRequest commentCreateRequest) {

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(RuntimeException::new);
        User user = userRepository.findById(commentCreateRequest.getUserId()).orElseThrow(RuntimeException::new);

        Comment comment = Comment.builder()
                .content(commentCreateRequest.getContent())
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .user(user)
                .schedule(schedule)
                .build();

        return commentRepository.save(comment).getId();
    }

    public CommentResponse findCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(RuntimeException::new);
        return CommentResponse.from(comment);
    }

    public List<CommentResponse> findComments(Long scheduleId) {
        return commentRepository.findByScheduleId(scheduleId).stream().map(CommentResponse::from).toList();
    }

    @Transactional
    public void updateComment(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(RuntimeException::new);
        User user = userRepository.findById(commentUpdateRequest.getUserId()).orElseThrow(RuntimeException::new);
        validateAuthor(comment, user);
        comment.updateContent(commentUpdateRequest.getContent());
        comment.updateModifiedAt(LocalDateTime.now());
    }

    @Transactional
    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(RuntimeException::new);
        User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
        validateAuthor(comment, user);
        commentRepository.delete(comment);
    }

    public void validateAuthor(Comment comment, User user) {
        if (!comment.getUser().equals(user)) {
            throw new RuntimeException();
        }
    }
}
