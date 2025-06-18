package com.spring.taskflow.domain.dto.user;

public class UserDeleteErrorResponseDto {
    // 속성
    private int status;
    private String message;

    // 생성자
    public UserDeleteErrorResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }

    // 기능(게터,세터)
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
