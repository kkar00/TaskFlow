package com.spring.taskflow.domain.entity;

import com.spring.taskflow.domain.dto.tasks.TaskCreateRequestDto;
import com.spring.taskflow.domain.dto.tasks.TaskUpdateRequestDto;
import com.spring.taskflow.domain.enumdata.Priority;
import com.spring.taskflow.domain.enumdata.Status;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Entity
@Table(name = "tasks")
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
    @JoinColumn(name = "created_by_id")
    private User createdById;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private User assigneeId;

    @Column (name = "start_date")
    private LocalDateTime startDate;

    @Column (name = "due_date")
    private LocalDateTime dueDate;

    @Column (name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.TODO;

    @CreatedDate
    @Column (name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column (name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column (name = "is_deleted", nullable = false)
    @ColumnDefault("true")
    private boolean isDeleted;

    @Column (name = "deleted_at")
    private LocalDateTime deletedAt;


    // 생성자
    /**
     * 기본생성자
     */
    public Task() {}

    /**
     * Task 생성시 사용되는 생성자
     */
    public Task(User loginUser , User assigneeUser , TaskCreateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
        this.priority = requestDto.getPriority();
        this.createdById = loginUser;
        this.assigneeId = assigneeUser;
        this.startDate = requestDto.getStartDate();
        this.dueDate = requestDto.getDueDate();
        this.status = requestDto.getStatus();
    }

    // 기능
    /**
     * 엔티티가 처음 저장되기 직전에 호출
     * createdAt, updatedAt을 현재 UTC 시간으로 초기화
     */
    @PrePersist
    public void onCreate() {
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        this.createdAt = now;
        this.updatedAt = now;
    }

    /**
     * 엔티티가 수정되기 직전에 호출
     * updatedAt을 현재 UTC 시간으로 초기화
     */
    @PreUpdate
    public void onUpdate() {
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        this.updatedAt = now;
    }

    /**
     * Task 수정 API 에서 사용하는 업데이트 기능
     */
    public void updateTask(User createdById, User assigneeId, TaskUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
        this.priority = requestDto.getPriority();
        this.createdById = createdById;
        this.assigneeId = assigneeId;
        this.startDate = requestDto.getStartDate();
        this.dueDate = requestDto.getDueDate();
        this.status = requestDto.getStatus();
    }

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

    public User getCreatedById() {
        return createdById;
    }

    public User getAssigneeId() {
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
