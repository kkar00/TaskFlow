package com.spring.taskflow.domain.dto.activitylog;

public class ActivityLogErrorResponseDto {
    // 속성
    private int status;
    private String message;

    // 생성자
    public ActivityLogErrorResponseDto() {}

    public ActivityLogErrorResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }

    // 기능(게터)
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
