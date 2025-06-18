package com.spring.taskflow.domain.dto.comments;

import com.spring.taskflow.domain.entity.Comment;

import java.time.LocalDateTime;

public class CommentUpdateResponseDto {
    //속성
    private Long commentId;
    private Long userId;
    private String userName;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    //셍성자

    public CommentUpdateResponseDto(Comment comment) {
        this.commentId = commentId;
        this.userId = userId;
        this.userName = userName;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    //기능

    public Long getCommentId() {
        return commentId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
