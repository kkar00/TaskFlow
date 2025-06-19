package com.spring.taskflow.domain.dto.user.login;

public class LoginResponseDto {
    private final String token;

    public LoginResponseDto(String token) {
        this.token = token;
    }

    // getter

    public String getToken() {
        return token;
    }
}
