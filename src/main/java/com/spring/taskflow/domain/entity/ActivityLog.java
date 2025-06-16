package com.spring.taskflow.domain.entity;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "activityLogs")
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
    private Integer userId;

    @Column(name = "task_id")
    private Integer taskId;

    @Column(name = "comment_id")
    private Integer commentId;

    @Column(name = "activity_type", nullable = false, length = 50)
    private String activityType;


    // 생성자
    /**
     * 기본생성자
     */
    public ActivityLog() {}


    // 기능

}
