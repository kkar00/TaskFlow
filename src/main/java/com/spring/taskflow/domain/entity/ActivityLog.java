package com.spring.taskflow.domain.entity;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "activity_logs")
@EntityListeners(AuditingEntityListener.class)
public class ActivityLog {
    // 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activityLog_id", nullable = false)
    private Long activityLogId;

    @Column(name = "requested_at", nullable = false)
    private LocalDateTime requestedAt;

    @Column(name = "ip_address", nullable = false, length = 50)
    private String ipAddress;

    @Column(name = "request_method", nullable = false, length = 50)
    private String requestMethod;

    @Column(name = "request_url", nullable = false, length = 255)
    private String requestUrl;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "activity_type", nullable = false, length = 50)
    private String activityType;

    // 생성자
    /**
     * 기본생성자
     */
    public ActivityLog() {}

    // 기능(게터)
    public Long getTaskId() {
        return taskId;
    }

    public String getActivityType() {
        return activityType;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    // 게터, 세터
    public Long getActivityLogId() {
        return activityLogId;
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

    public Long getCommentId() {
        return commentId;
    }

    public void setActivityLogId(Long activityLogId) {
        this.activityLogId = activityLogId;
    }

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }
}