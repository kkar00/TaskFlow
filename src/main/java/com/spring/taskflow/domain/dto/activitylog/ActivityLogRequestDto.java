package com.spring.taskflow.domain.dto.activitylog;

import java.security.PrivateKey;
import java.time.LocalDateTime;

public class ActivityLogRequestDto {
    // 속성
    private Long taskId;
    private String activityType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String sortBy;
    private String sortOrder;

    // 생성자
    public ActivityLogRequestDto() {};

    public ActivityLogRequestDto(Long taskId, String activityType, LocalDateTime startDate, LocalDateTime endDate, String sortBy, String sortOrder) {
        this.taskId = taskId;
        this.activityType = activityType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sortBy = sortBy;
        this.sortOrder = sortOrder;
    }

    // 기능(게터)
    public Long getTaskId() {
        return taskId;
    }

    public String getActivityType() {
        return activityType;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getSortBy() {
        return sortBy;
    }

    public String getSortOrder() {
        return sortOrder;
    }
}
