package com.spring.taskflow.domain.dto.activitylog;

import java.time.LocalDateTime;

public class ActivityLogResponseDto {
    // 속성
    private Long activityLogId;
    private LocalDateTime requestedAt;
    private String ipAddress;
    private String requestMethod;
    private String requestUrl;
    private Long userId;
    private Long taskId;
    private Long commentId;
    private String activityType;

    // 생성자
    public ActivityLogResponseDto() {}

    public ActivityLogResponseDto(Long activityLogId, LocalDateTime requestedAt, String ipAddress,
                                  String requestMethod, String requestUrl, Long userId, Long taskId,
                                  Long commentId, String activityType) {
        this.activityLogId = activityLogId;
        this.requestedAt = requestedAt;
        this.ipAddress = ipAddress;
        this.requestMethod = requestMethod;
        this.requestUrl = requestUrl;
        this.userId = userId;
        this.taskId = taskId;
        this.commentId = commentId;
        this.activityType = activityType;
    }

    // 기능(게터)
    public Long getActivityLogId() {
        return activityLogId;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public String getActivityType() {
        return activityType;
    }
}
