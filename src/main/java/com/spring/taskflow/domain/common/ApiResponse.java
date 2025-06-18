package com.spring.taskflow.domain.common;

import java.time.LocalDateTime;

public class ApiResponse<T> {
    // 속성
    private boolean succeess;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    // 생성자
    public ApiResponse(boolean success, String message, T data) {
        this.succeess = success;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    // 기능

    public boolean isSucceess() {
        return succeess;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
