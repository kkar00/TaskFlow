package com.spring.taskflow.domain.dto.user.login;

public class LoginRequestDto {
    private final String password;
    private final String userName;

    public LoginRequestDto(String password, String userName) {
        this.password = password;
        this.userName = userName;
    }

    // Getter
    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }
}
