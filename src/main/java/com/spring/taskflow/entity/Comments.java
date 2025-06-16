package com.spring.taskflow.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comments { // extends BaseEntity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Tasks tasks;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @Column(nullable = false, length = 50)
    private String userName;
    @Column(nullable = false)
    private String content;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private Boolean isDeleted;
    private LocalDateTime deleteAt;

    public Comments(){}

    public Comments(String userName, String content){
        this.userName = userName;
        this.content = content;
    }
}
