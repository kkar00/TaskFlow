package com.spring.taskflow.common;

import java.time.LocalDateTime;

public class ApiResponse<T> {
    private final Boolean success;
    private final String message;
    private final T data;
    private final LocalDateTime timestamp;

    public ApiResponse(Boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    // getter

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public T getData() {
        return data;
    }
}



