package com.nbcam.schedule_management_v2.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Comment {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @Builder
    private Comment(String content, LocalDateTime createdAt, LocalDateTime modifiedAt, User user, Schedule schedule) {
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.user = user;
        this.schedule = schedule;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
