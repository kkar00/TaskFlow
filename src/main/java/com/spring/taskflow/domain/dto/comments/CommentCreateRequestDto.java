package com.spring.taskflow.domain.dto.comments;

public class CommentCreateRequestDto {
    //속성
    private String content;
    private Long taskId;
    //로그인 기능 없을때 사용
    private Long userId;
    //명시적으로 기본값 false세팅
    private boolean isDeleted;
    //생성자
    public CommentCreateRequestDto() {
        this.isDeleted = false; //  기본값 설정
    }
    //기능
    //댓글 생성서비스에 사용
    public String getContent() {
        return content;
    }

    public Long getTaskId() {
        return taskId;
    }

    public Long getUserId() {
        return userId;
    }

    public Boolean isDeleted() {
        return isDeleted;
    }
    //댓글생성컨트롤러에 쓰임
    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
