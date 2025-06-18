package com.spring.taskflow.domain.dto.tasks;

import com.spring.taskflow.domain.enumdata.Priority;
import com.spring.taskflow.domain.enumdata.Status;

import java.time.LocalDateTime;

public class TaskUpdateRequestDto {
    private String title;
    private String description;
    private Priority priority;
    private Long assigneeId;
    private LocalDateTime startDate;
    private LocalDateTime dueDate;
    private Status status;
    private LocalDateTime updatedAt;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public Long getAssigneeId() {
        return assigneeId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
