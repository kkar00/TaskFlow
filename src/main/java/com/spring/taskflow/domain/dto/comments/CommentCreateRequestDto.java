package com.spring.taskflow.domain.dto.comments;

public class CommentCreateRequestDto {
    //속성
    private String content;
    private Long taskId;
    //생성자

    //기능
    //댓글 생성서비스에 사용
    public String getContent() {
        return content;
    }
}
