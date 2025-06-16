package com.spring.taskflow.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Entity
@Table(name = "users")
public class User {
    //속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "user_email", nullable = false, length = 100)
    private String userEmail;
    @Column(nullable = false)
    private String password;
    @Column(name = "user_name", nullable = false, length = 50)
    private String username;
    @Column(nullable = false, length = 50)
    private String role;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    //생성자

    /**
     * 기본생성자
     */
    public User() {

    }
    //기능

    /**
     * 엔티티가 처음 저장되기 직전에 호출
     * createdAt, updatedAt을 현재UTC시간으로 초기화
     */
    @PrePersist
    public void onCreate() {
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        this.createdAt = now;
        this.updatedAt = now;
    }

    /**
     * 엔티티가 수정되기 직전에 호출
     * updatedAt을 현재 UTC시간으로 초기화
     */
    @PreUpdate
    public void onUpdate() {
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        this.updatedAt = now;
    }
}
