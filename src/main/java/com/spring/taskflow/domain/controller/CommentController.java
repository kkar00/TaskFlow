package com.spring.taskflow.domain.controller;

import com.spring.taskflow.domain.dto.comments.CommentCreateRequestDto;
import com.spring.taskflow.domain.dto.comments.CommentCreateResponseDto;
import com.spring.taskflow.domain.dto.comments.CommentGetRequestDto;
import com.spring.taskflow.domain.dto.comments.CommentGetResponseDto;
import com.spring.taskflow.domain.entity.Comment;
import com.spring.taskflow.domain.repository.CommentRepository;
import com.spring.taskflow.domain.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
public class CommentController {
    //속성
    private final CommentService commentService;
    private final CommentRepository commentRepository;

    //생성자
    public CommentController(CommentService commentService, CommentRepository commentRepository) {
        this.commentService = commentService;
        this.commentRepository = commentRepository;
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

    /**
     * 댓글 조회 API
     */
    @GetMapping("/comments")
    public List<CommentGetResponseDto> getComments(CommentGetRequestDto requestDto) {
        return commentService.getComments(requestDto);
    }

    /**
     *
     * 댓글 단건 조회 API
     */
    @GetMapping("/comments/{commentId}")
    public CommentGetResponseDto getComment(@PathVariable Long commentId) {
        return commentService.getCommentById(commentId);
    }
}
