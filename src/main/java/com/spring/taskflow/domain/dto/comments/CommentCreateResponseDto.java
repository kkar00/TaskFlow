package com.spring.taskflow.domain.dto.comments;

import com.spring.taskflow.domain.entity.Comment;

import java.time.LocalDateTime;

public class CommentCreateResponseDto {
    //속성
    private Long commentId;
    private Long userId;
    private String username;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    //생성자
    public CommentCreateResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.userId = comment.getUser().getUserId();
        this.username = comment.getUsername();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();
    }
    //기능

}
