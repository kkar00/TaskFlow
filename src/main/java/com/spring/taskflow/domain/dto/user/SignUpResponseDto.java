package com.spring.taskflow.domain.dto.user;

import com.spring.taskflow.domain.entity.User;
import com.spring.taskflow.domain.enumdata.Role;

import java.time.LocalDateTime;

public class SignUpResponseDto {
    private final Long userId;
    private final String userEmail;
    private final String userName;
    private final Role role;
    private final LocalDateTime createdAt;

    public SignUpResponseDto(User user) {
        this.userId = user.getUserId();
        this.userEmail = user.getUserEmail();
        this.userName = user.getUserName();
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

    public Role getRole() {
        return role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
