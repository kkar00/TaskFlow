package com.spring.taskflow.domain.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "Tasks")
@EntityListeners(AuditingEntityListener.class)
public class Task {
    // 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", nullable = false)
    private Long taskId;

    @Column (name = "title", nullable = false , length = 50)
    private String title;

    @Column (name = "description", nullable = false)
    private String description;

    @Column (name = "priority", nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdBy_id")
    private User createdById;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private User assigneeId;

    @Column (name = "start_date")
    @ColumnDefault("TODO")
    private LocalDateTime startDate;

    @Column (name = "due_date")
    private LocalDateTime dueDate;

    @Column (name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @CreatedDate
    @Column (name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column (name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column (name = "is_deleted", nullable = false)
    private boolean isDeleted;

    @Column (name = "deleted_at")
    private LocalDateTime deletedAt;


    public enum Priority {
        LOW, MEDIUM, HIGH
    }
    public enum Status {
        TODO, IN_PROGRESS, DONE
    }


    // 생성자
    /**
     * 기본생성자
     */
    public Task() {}


    // 기능
}
