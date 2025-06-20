package com.spring.taskflow.domain.controller;

import com.spring.taskflow.common.ApiResponse;
import com.spring.taskflow.domain.dto.tasks.*;
import com.spring.taskflow.domain.service.JwtService;
import com.spring.taskflow.domain.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    // 속성
    private final TaskService taskService;
    private final JwtService jwtService;

    // 생성자
    public TaskController(TaskService taskService, JwtService jwtService) {
        this.taskService = taskService;
        this.jwtService = jwtService;
    }

    // 기능
    /**
     * Task 생성 API
     */
    @PostMapping
    public ResponseEntity<ApiResponse<TaskCreateResponseDto>> createTaskAPI(
            HttpServletRequest request,
            @Valid @RequestBody TaskCreateRequestDto requestDto
    ) {
        // 1. 헤더에서 토큰 추출
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("토근이 올바르지 않습니다");
        }
        String token = authHeader.substring(7);

        // 2. 토큰 검증
        Long userId = jwtService.verifyToken(token);

        String ipAddress = request.getRemoteAddr();

        ApiResponse<TaskCreateResponseDto> responseDto = taskService.createTaskService(userId, requestDto, ipAddress);
        ResponseEntity<ApiResponse<TaskCreateResponseDto>> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    /**
     * Task 조회 API
     */
    @GetMapping
    public ResponseEntity<ApiResponse<TaskListDto>> getTaskLiskAPI(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        ApiResponse<TaskListDto> responseDto = taskService.getTaskListService(page, size);
        ResponseEntity<ApiResponse<TaskListDto>> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    /**
     * Task 단건 조회 API
     */
    @GetMapping("/{taskId}")
    public ResponseEntity<ApiResponse<TaskGetDetailResponseDto>> getTaskDetailAPI(@PathVariable("taskId") Long taskId) {
        ApiResponse<TaskGetDetailResponseDto> responseDto = taskService.getTaskDetialService(taskId);
        ResponseEntity<ApiResponse<TaskGetDetailResponseDto>> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    /**
     * Task 제목 검색 API
     */
    @GetMapping("/searchtitle")
    public ResponseEntity<ApiResponse<TaskSearchListDto>> getTaskSearchTitleAPI(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestBody TaskSearchListRequestDto requestDto
    ) {
        ApiResponse<TaskSearchListDto> responseDto = taskService.getTaskSearchTitleService(page, size, requestDto);
        ResponseEntity<ApiResponse<TaskSearchListDto>> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    /**
     * Task 내용 검색 API
     */
    @GetMapping("/searchdescription")
    public ResponseEntity<ApiResponse<TaskSearchListDto>> getTaskSearchDescriptionAPI(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestBody TaskSearchListRequestDto requestDto
    ) {
        ApiResponse<TaskSearchListDto> responseDto = taskService.getTaskSearchDescriptionService(page, size, requestDto);
        ResponseEntity<ApiResponse<TaskSearchListDto>> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    /**
     * Task 수정 API
     */
    @PatchMapping("/{taskId}")
    public ResponseEntity<ApiResponse<TaskUpdateResponseDto>> updateTaskAPI(
            HttpServletRequest request,
            @PathVariable("taskId") Long taskId,
            @RequestBody TaskUpdateRequestDto requestDto
    ) {
        // 1. 헤더에서 토큰 추출
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("토근이 올바르지 않습니다");
        }
        String token = authHeader.substring(7);

        // 2. 토큰 검증
        Long userId = jwtService.verifyToken(token);

        ApiResponse<TaskUpdateResponseDto> responseDto = taskService.updateTaskService(userId, taskId, requestDto);
        ResponseEntity<ApiResponse<TaskUpdateResponseDto>> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    /**
     * Task Status 수정 API
     */
    @PatchMapping("status/{taskId}")
    public ResponseEntity<ApiResponse<TaskUpdateStatusResponseDto>> updateTaskStatusAPI(
            HttpServletRequest request,
            @PathVariable("taskId") Long taskId,
            @RequestBody TaskUpdateStatusRequestDto requestDto
    ) {
        // 1. 헤더에서 토큰 추출
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("토근이 올바르지 않습니다");
        }
        String token = authHeader.substring(7);

        // 2. 토큰 검증
        Long userId = jwtService.verifyToken(token);

        ApiResponse<TaskUpdateStatusResponseDto> responseDto = taskService.updateTaskStatusService(userId, taskId, requestDto);
        ResponseEntity<ApiResponse<TaskUpdateStatusResponseDto>> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    /**
     * Task 삭제 API
     */
    @DeleteMapping("/{taskId}")
    public ResponseEntity<ApiResponse<Object>> deleteTaskAPI(
            HttpServletRequest request,
            @PathVariable("taskId") Long taskId
    ) {
        // 1. 헤더에서 토큰 추출
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("토근이 올바르지 않습니다");
        }
        String token = authHeader.substring(7);

        // 2. 토큰 검증
        Long userId = jwtService.verifyToken(token);

        ApiResponse<Object> responseDto = taskService.deleteTaskService(userId, taskId);
        ResponseEntity<ApiResponse<Object>> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }
}
