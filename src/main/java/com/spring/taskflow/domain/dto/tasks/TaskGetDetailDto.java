package com.spring.taskflow.domain.dto.tasks;

import com.spring.taskflow.domain.entity.Task;
import com.spring.taskflow.domain.entity.User;
import com.spring.taskflow.domain.enumdata.Priority;
import com.spring.taskflow.domain.enumdata.Status;

import java.time.LocalDateTime;
import java.util.Optional;

public class TaskGetDetailDto {
    private Long taskId;
    private String title;
    private String description;
    private Priority priority;
    private Long createdById;
    private Long assigneeId;
    private LocalDateTime startDate;
    private LocalDateTime dueDate;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TaskGetDetailDto(Task task) {
        this.taskId = task.getTaskId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.priority = task.getPriority();
        this.createdById = Optional.ofNullable(task.getCreatedById()).map(User::getUserId).orElse(null);
        this.assigneeId = Optional.ofNullable(task.getAssigneeId()).map(User::getUserId).orElse(null);
        this.startDate = task.getStartDate();
        this.dueDate = task.getDueDate();
        this.status = task.getStatus();
        this.createdAt = task.getCreatedAt();
        this.updatedAt = task.getUpdatedAt();
    }

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
        return createdById;
    }

    public Long getAssignee() {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
