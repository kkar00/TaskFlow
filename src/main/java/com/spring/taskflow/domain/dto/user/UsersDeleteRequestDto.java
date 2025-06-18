package com.spring.taskflow.domain.dto.user;

public class UsersDeleteRequestDto {
    // 속성
    private String userEmail;
    private String password;

    // 생성자
    public UsersDeleteRequestDto() {}

    // 기능(게터)
    public String getUserEmail() {
        return userEmail;
    }
    public String getPassword() {
        return password;
    }
}
