package com.spring.taskflow.domain.controller;

import com.spring.taskflow.common.ApiResponse;
import com.spring.taskflow.domain.dto.tasks.*;
import com.spring.taskflow.domain.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    // 속성
    private final TaskService taskService;

    // 생성자
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // 기능
    /**
     * Task 생성 API
     */
    @PostMapping
    public ResponseEntity<ApiResponse<TaskCreateResponseDto>> createTaskAPI(@Valid @RequestBody TaskCreateRequestDto requestDto) {
        // 1. 헤더에서 토큰 추출

        // 2. 토큰 검증
        Long userId = 1L;

        ApiResponse<TaskCreateResponseDto> responseDto = taskService.createTaskService(userId, requestDto);
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
     * Task 수정 API
     */
    @PatchMapping("/{taskId}")
    public ResponseEntity<ApiResponse<TaskUpdateResponseDto>> updateTaskAPI(
            @PathVariable("taskId") Long taskId,
            @RequestBody TaskUpdateRequestDto requestDto
    ) {
        // 1. 헤더에서 토큰 추출

        // 2. 토큰 검증
        Long userId = 1L;

        ApiResponse<TaskUpdateResponseDto> responseDto = taskService.updateTaskService(userId, taskId, requestDto);
        ResponseEntity<ApiResponse<TaskUpdateResponseDto>> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    /**
     * Task 삭제 API
     */
    @DeleteMapping("/{taskId}")
    public ResponseEntity<ApiResponse<Object>> deleteTaskAPI(@PathVariable("taskId") Long taskId) {
        // 1. 헤더에서 토큰 추출

        // 2. 토큰 검증
        Long userId = 1L;

        ApiResponse<Object> responseDto = taskService.deleteTaskService(userId, taskId);
        ResponseEntity<ApiResponse<Object>> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }
}
