package com.spring.taskflow.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "activityLog")

public class AcitvityLog {

    // 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id", nullable = false)
    private Long id;

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
    private String activityIype;

    // 생성자
    public AcitvityLog () {}


    // 기능



}
