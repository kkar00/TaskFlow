package com.spring.taskflow.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;

    // 필요한 경우 기본 생성자만 있어도 OK
    public Task() {}
}
