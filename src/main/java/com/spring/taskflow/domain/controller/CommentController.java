package com.spring.taskflow.domain.controller;

import com.spring.taskflow.common.ApiResponse;
import com.spring.taskflow.domain.dto.comments.*;
import com.spring.taskflow.domain.service.CommentService;
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
    public ResponseEntity<ApiResponse<CommentCreateResponseDto>> CreateCommentAPI(@RequestBody CommentCreateRequestDto requestDto) {
        CommentCreateResponseDto responseDto = commentService.createCommentService(requestDto);
        ApiResponse<CommentCreateResponseDto> apiResponse = new ApiResponse<>(
                true, "댓글 생성이 완료되었습니다.", responseDto);
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * 댓글 조회 API
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<CommentGetResponseDto>>> getCommentsAPI(CommentGetRequestDto requestDto) {
        List<CommentGetResponseDto> responseDtoList = commentService.getComments(requestDto);
        ApiResponse<List<CommentGetResponseDto>> apiResponse = new ApiResponse<>(
                true, "댓글 조회가 완료되었습니다.", responseDtoList);
        return ResponseEntity.ok(apiResponse);
    }

    /**
     *
     * 댓글 단건 조회 API
     */
    @GetMapping("/{commentId}")
    public ResponseEntity<ApiResponse<CommentGetResponseDto>> getCommentAPI(@PathVariable("commentId") Long commentId) {
        CommentGetResponseDto responseDto = commentService.getCommentById(commentId);
        ApiResponse<CommentGetResponseDto> apiResponse = new ApiResponse<>(
                true, "댓글 단건 조회가 완료되었습니다.", responseDto
        );
        return  ResponseEntity.ok(apiResponse);
    }

    /**
     * 댓글 수정 API
     */
    @PatchMapping("/{commentId}")
    public ResponseEntity<ApiResponse<CommentUpdateResponseDto>> updateCommentAPI(
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentUpdateRequestDto requestDto) {
        CommentUpdateResponseDto responseDto = commentService.updateComment(commentId, requestDto);
        ApiResponse<CommentUpdateResponseDto> apiResponse = new ApiResponse<>(true, "댓글 수정이 완료되었습니다.", responseDto);
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * 댓글 삭제 API
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse<Void>> deleteCommentAPI(@PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);
        ApiResponse<Void> apiResponse = new ApiResponse<>(
                true, "댓글 삭제가 완료되었습니다.", null
        );
        return ResponseEntity.ok(apiResponse);
    }
}
