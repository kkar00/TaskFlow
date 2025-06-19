package com.spring.taskflow.domain.dto.user.deleteUser;

import java.time.LocalDateTime;

public class UserDeleteErrorResponseDto {
    // 속성
    private boolean success;
    private String message;
    private Object data;
    private LocalDateTime timestamp;

    // 생성자
    public UserDeleteErrorResponseDto(String message) {
        this.success = false;
        this.message = message;
        this.data = null;
        this.timestamp = LocalDateTime.now();
    }

    // 기능(게터,세터)

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
