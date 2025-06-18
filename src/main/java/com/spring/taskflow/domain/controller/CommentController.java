package com.spring.taskflow.domain.controller;

import com.spring.taskflow.common.ApiResponse;
import com.spring.taskflow.domain.dto.comments.*;
import com.spring.taskflow.domain.service.CommentService;
import com.spring.taskflow.domain.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    //속성
    private final CommentService commentService;
    private final JwtService jwtService;

    //생성자
    public CommentController(CommentService commentService, JwtService jwtService) {
        this.commentService = commentService;
        this.jwtService = jwtService;
    }
    //기능
    /**
     * 댓글 생성 API
     */
    @PostMapping
    public ResponseEntity<ApiResponse<CommentCreateResponseDto>> CreateCommentAPI(
            @RequestBody CommentCreateRequestDto requestDto,
            HttpServletRequest request) {
        try{
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(new ApiResponse<>(false, "토큰이 존재하지 않습니다.", null));
            }

            String token = authHeader.substring(7); // "Bearer " 제거
            Long userId = jwtService.verifyToken(token);
            CommentCreateResponseDto responseDto = commentService.createCommentService(requestDto, userId);
            ApiResponse<CommentCreateResponseDto> apiResponse = new ApiResponse<>(
                true, "댓글 생성이 완료되었습니다.", responseDto);
            return ResponseEntity.ok(apiResponse);}
        catch (RuntimeException e) {
                ApiResponse<CommentCreateResponseDto> errorResponse = new ApiResponse<>(
                        false, "로그인이 필요합니다.", null);

                return ResponseEntity.badRequest().body(errorResponse);
            }
    }

    /**
     * 댓글 조회 API
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<CommentGetResponseDto>>> getCommentsAPI(
            @RequestParam Long taskId,
            @RequestParam(required = false) String keyword) {
        CommentGetRequestDto requestDto = new CommentGetRequestDto();
        requestDto.setTaskId(taskId);
        requestDto.setKeyword(keyword);
        List<CommentGetResponseDto> responseDtoList = commentService.getComments(requestDto);
        System.out.println("taskId: " + requestDto.getTaskId());
        System.out.println("keyword: " + requestDto.getKeyword());
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
            @RequestBody CommentUpdateRequestDto requestDto,
            HttpServletRequest request) {
        try{

            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(new ApiResponse<>(false, "토큰이 존재하지 않습니다.", null));
            }

            String token = authHeader.substring(7); // "Bearer " 제거
            Long userId = jwtService.verifyToken(token);
            CommentUpdateResponseDto responseDto = commentService.updateComment(commentId, requestDto);
            ApiResponse<CommentUpdateResponseDto> apiResponse = new ApiResponse<>(true, "댓글 수정이 완료되었습니다.", responseDto);
            return ResponseEntity.ok(apiResponse);}
        catch (RuntimeException e) {
            ApiResponse<CommentUpdateResponseDto> errorResponse = new ApiResponse<>(
                    false, "로그인이 필요합니다.", null);
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    /**
     * 댓글 삭제 API
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse<Void>> deleteCommentAPI(
            @PathVariable("commentId") Long commentId,
            HttpServletRequest request) {
        try{

            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(new ApiResponse<>(false, "토큰이 존재하지 않습니다.", null));
            }
            String token = authHeader.substring(7); // "Bearer " 제거
            Long userId = jwtService.verifyToken(token);
            commentService.deleteComment(commentId);
            ApiResponse<Void> apiResponse = new ApiResponse<>(
                true, "댓글 삭제가 완료되었습니다.", null);
            return ResponseEntity.ok(apiResponse);}
        catch (RuntimeException e) {
            ApiResponse<Void> errorResponse = new ApiResponse<>(
                    false, "로그인이 필요합니다.", null);
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
