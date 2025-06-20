package com.spring.taskflow.domain.dto.user;

public class LoginResponseDto {
    private final String token;

    public LoginResponseDto(String token) {
        this.token = "Bearer " + token;
    }

    // getter

    public String getToken() {
        return token;
    }
}
