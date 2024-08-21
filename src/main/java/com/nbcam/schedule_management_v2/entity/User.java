package com.nbcam.schedule_management_v2.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Schedule> schedule = new ArrayList<>();

    @Builder
    private User(String name, String email, LocalDateTime createdAt, LocalDateTime modifiedAt, String password, List<Schedule> schedule) {
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.password = password;
        this.schedule = schedule;
    }
}
