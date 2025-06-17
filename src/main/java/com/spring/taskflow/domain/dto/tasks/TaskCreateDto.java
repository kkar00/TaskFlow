package com.spring.taskflow.domain.dto.tasks;

import com.spring.taskflow.domain.entity.Task;
import com.spring.taskflow.domain.enumdata.Priority;
import com.spring.taskflow.domain.enumdata.Status;

import java.time.LocalDateTime;

public class TaskCreateDto {
    // 속성
    private Long taskId;
    private String title;
    private String description;
    private Priority priority;
    private Long createdBy;
    private Long assignee;
    private LocalDateTime startDate;
    private LocalDateTime dueDate;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 생성자
    public TaskCreateDto(Task task) {
        this.taskId = task.getTaskId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.priority = task.getPriority();
        this.createdBy = task.getCreatedById().getUserId();
        this.assignee = task.getAssigneeId().getUserId();
        this.startDate = task.getStartDate();
        this.dueDate = task.getDueDate();
        this.status = task.getStatus();
        this.createdAt = task.getCreatedAt();
        this.updatedAt = task.getUpdatedAt();
    }

    // 기능
    // 게터
    public Long getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public Long getAssignee() {
        return assignee;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
