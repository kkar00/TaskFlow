package com.spring.taskflow.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Table(name = "Tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;

    @Column (name = "title", nullable = false , length = 50)
    private String title;

    @Column (name = "description", nullable = false)
    private String description;

    @Column (name = "priority", nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdBy_id", nullable = true)
    private User createdById;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id", nullable = true)
    private User assigneeId;

    @Column (name = "start_date", nullable = true)
    @ColumnDefault("TODO")
    private LocalDateTime startDate;

    @Column (name = "due_date", nullable = true)
    private LocalDateTime dueDate;

    @Column (name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column (name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column (name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column (name = "is_deleted", nullable = false)
    private boolean isDeleted;

    @Column (name = "deleted_at", nullable = true)
    private LocalDateTime deletedAt;


    public enum Priority {
        LOW, MEDIUM, HIGH
    }
    public enum Status {
        TODO, IN_PROGRESS, DONE
    }
}
