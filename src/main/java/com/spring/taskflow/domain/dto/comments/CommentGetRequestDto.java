package com.spring.taskflow.domain.dto.comments;

public class CommentGetRequestDto {
    //속성
    private Long taskId;
    private String keyword;

    //생성자

    //기능

    public Long getTaskId() {
        return taskId;
    }

    public String getKeyword() {
        return keyword;
    }
}
