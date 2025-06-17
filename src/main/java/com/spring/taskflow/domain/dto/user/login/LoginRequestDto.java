package com.spring.taskflow.domain.dto.user.login;

public class LoginRequestDto {
    private final Long userId;
    private final String userEmail;
    private final String password;

    public LoginRequestDto(Long userId, String userEmail, String password) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.password = password;
    }

    // Getter
    public Long getUserId() {
        return userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getPassword() {
        return password;
    }
}
