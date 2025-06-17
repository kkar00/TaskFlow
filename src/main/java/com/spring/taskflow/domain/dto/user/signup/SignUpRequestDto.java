package com.spring.taskflow.domain.dto.user.signup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SignUpRequestDto {
    @NotBlank(message = "null/공백은 입력 불가합니다.")
    @Email(message = "email 형식을 입력하세요.")
    private String userEmail;
    @NotBlank(message = "null/공백은 입력 불가합니다.")
    private String password;
    @NotBlank(message = "null/공백은 입력 불가합니다.")
    private String userName;

    // Getter
    public String getUserEmail() {
        return userEmail;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }
}