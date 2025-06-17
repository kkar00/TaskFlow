package com.spring.taskflow.domain.dto.tasks;

import java.time.LocalDateTime;

public class TaskDeleteResponseDto {
    private boolean seccess;
    private String message;
    private LocalDateTime timestamp;

    public TaskDeleteResponseDto(boolean seccess, String message) {
        this.seccess = seccess;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public boolean isSeccess() {
        return seccess;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
