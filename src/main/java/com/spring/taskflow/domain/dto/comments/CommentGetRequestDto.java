package com.spring.taskflow.domain.dto.comments;

public class CommentGetRequestDto {
    //속성
    private Long taskId;
    private String keyword;

    //생성자
    //기본생성자
    public CommentGetRequestDto() {

    }

    //기능

    public Long getTaskId() {
        return taskId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
