package com.spring.taskflow.domain.controller;

import com.spring.taskflow.domain.dto.comments.CommentCreateRequestDto;
import com.spring.taskflow.domain.dto.comments.CommentCreateResponseDto;
import com.spring.taskflow.domain.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentController {
    //속성
    private final CommentService commentService;
    //생성자
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    //기능
    /**
     * 댓글 생성 API
     */
    @PostMapping
    public ResponseEntity<CommentCreateResponseDto> CreateCommentAPI(@RequestBody CommentCreateRequestDto requestDto) {
        CommentCreateResponseDto responseDto = commentService.createCommentService(requestDto);
        ResponseEntity<CommentCreateResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }
}
