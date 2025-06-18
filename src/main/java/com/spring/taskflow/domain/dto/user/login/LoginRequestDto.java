package com.spring.taskflow.domain.dto.user.login;

public class LoginRequestDto {
    private final String password;
    private final String userEmail;

    public LoginRequestDto(String password, String userEmail) {
        this.password = password;
        this.userEmail = userEmail;
    }

    // Getter
    public String getPassword() {
        return password;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
