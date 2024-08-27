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
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String username;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Builder
    private User(String username, String email, LocalDateTime createdAt, LocalDateTime modifiedAt, String password, Role role) {
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.password = password;
        this.role = role;
    }

    public void updateUsername(String username) {
        this.username = username;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
