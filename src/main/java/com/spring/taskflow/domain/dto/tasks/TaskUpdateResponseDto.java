package com.spring.taskflow.domain.dto.tasks;

import java.time.LocalDateTime;

public class TaskUpdateResponseDto {
    // 속성
    private boolean success;
    private String message;
    private TaskUpdateDto data;
    private LocalDateTime timestamp;

    // 생성자
    public TaskUpdateResponseDto(boolean success, String message, TaskUpdateDto data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    // 기능
    // 게터
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public TaskUpdateDto getDate() {
        return data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
