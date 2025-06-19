package com.spring.taskflow.domain.dto.user.deleteUser;

public class UserDeleteRequestDto {
    // 속성
    private String userEmail;
    private String password;

    // 생성자
    public UserDeleteRequestDto() {}

    // 기능(게터)
    public String getUserEmail() {
        return userEmail;
    }
    public String getPassword() {
        return password;
    }
}
