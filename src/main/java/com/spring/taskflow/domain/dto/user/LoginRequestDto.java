package com.spring.taskflow.domain.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequestDto {
    @NotBlank(message = "null/공백은 입력 불가합니다.")
    private final String password;
    @NotBlank(message = "null/공백은 입력 불가합니다.")
    @Email(message = "email 형식을 입력하세요.")
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
