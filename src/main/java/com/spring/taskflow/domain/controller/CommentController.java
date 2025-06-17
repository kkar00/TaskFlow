package com.spring.taskflow.domain.controller;

import com.spring.taskflow.domain.dto.comments.*;
import com.spring.taskflow.domain.repository.CommentRepository;
import com.spring.taskflow.domain.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 댓글 조회 API
     */
    @GetMapping
    public List<CommentGetResponseDto> getCommentsAPI(CommentGetRequestDto requestDto) {
        return commentService.getComments(requestDto);
    }

    /**
     *
     * 댓글 단건 조회 API
     */
    @GetMapping("/{commentId}")
    public CommentGetResponseDto getCommentAPI(@PathVariable("commentId") Long commentId) {
        return commentService.getCommentById(commentId);
    }

    /**
     * 댓글 수정 API
     */
    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentUpdateResponseDto> updateCommentAPI(
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentUpdateRequestDto requestDto) {
        CommentUpdateResponseDto responseDto = commentService.updateComment(commentId, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * 댓글 삭제 API
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteCommentAPI(@PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
