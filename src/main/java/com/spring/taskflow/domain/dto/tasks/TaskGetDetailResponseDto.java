package com.spring.taskflow.domain.dto.tasks;

import java.time.LocalDateTime;

public class TaskGetDetailResponseDto {
    private boolean seccess;
    private String message;
    private TaskGetDetailDto data;
    private LocalDateTime timestamp;

    public TaskGetDetailResponseDto(boolean seccess, String message, TaskGetDetailDto data) {
        this.seccess = seccess;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public boolean isSeccess() {
        return seccess;
    }

    public String getMessage() {
        return message;
    }

    public TaskGetDetailDto getDate() {
        return data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
