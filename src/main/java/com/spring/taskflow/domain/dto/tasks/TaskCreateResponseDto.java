package com.spring.taskflow.domain.dto.tasks;

import java.time.LocalDateTime;

public class TaskCreateResponseDto<Data> {
    // 속성
    private boolean success;
    private String message;
    private Data date;
    private LocalDateTime timestamp;

    // 생성자

    public TaskCreateResponseDto(boolean success, String message, Data date) {
        this.success = success;
        this.message = message;
        this.date = date;
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

    public Data getDate() {
        return date;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
