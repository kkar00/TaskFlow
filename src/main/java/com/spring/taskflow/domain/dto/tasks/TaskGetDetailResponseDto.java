package com.spring.taskflow.domain.dto.tasks;

import java.time.LocalDateTime;

public class TaskGetDetailResponseDto {
    private boolean seccess;
    private String message;
    private TaskGetDetailDto date;
    private LocalDateTime timestamp;

    public TaskGetDetailResponseDto(boolean seccess, String message, TaskGetDetailDto date) {
        this.seccess = seccess;
        this.message = message;
        this.date = date;
        this.timestamp = LocalDateTime.now();
    }

    public boolean isSeccess() {
        return seccess;
    }

    public String getMessage() {
        return message;
    }

    public TaskGetDetailDto getDate() {
        return date;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
