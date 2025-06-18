package com.spring.taskflow.domain.dto.tasks;

import com.spring.taskflow.domain.enumdata.Priority;
import com.spring.taskflow.domain.enumdata.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class TaskCreateRequestDto {
    @NotBlank(message = "제목은 필수 입력값입니다.")
    private String title;
    @NotBlank(message = "내용은 필수 입력값입니다.")
    private String description;
    @NotNull(message = "우선순위는 필수 입력값입니다.")
    private Priority priority;
    private Long assigneeId;
    private LocalDateTime startDate;
    private LocalDateTime dueDate;
    private Status status;

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
}
