package com.spring.taskflow.domain.dto.user.deleteUser;

import java.time.LocalDateTime;

public class UserDeleteResponseDto {
    // 속성
    private boolean success;
    private String message;
    private Object data;
    private LocalDateTime timestamp;

    // 생성자
    public UserDeleteResponseDto(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
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
