package com.spring.taskflow.domain.dto.user.signup;

import com.spring.taskflow.domain.entity.User;

import java.time.LocalDateTime;

public class SignUpResponseDto {
    private final Long userId;
    private final String userEmail;
    private final String userName;
    private final String role; // TODO Enum으로 바꾸기
    private final LocalDateTime createdAt;

    public SignUpResponseDto(User user) {
        this.userId = user.getUserId();
        this.userEmail = user.getUserEmail();
        this.userName = user.getUsername();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt();
    }

    // getter

    public Long getUserId() {
        return userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public String getRole() {
        return role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
