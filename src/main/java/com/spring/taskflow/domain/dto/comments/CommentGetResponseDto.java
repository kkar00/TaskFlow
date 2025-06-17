package com.spring.taskflow.domain.dto.comments;

import com.spring.taskflow.domain.entity.Comment;

import java.time.LocalDateTime;

public class CommentGetResponseDto {
    private Long commentId;
    private Long userId;
    private String username;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CommentGetResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.userId = comment.getUser().getUserId();
        this.username = comment.getUser().getUsername();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();
    }

    public Long getCommentId() {
        return commentId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
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
