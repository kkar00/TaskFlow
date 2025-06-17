package com.spring.taskflow.domain.controller;

import com.spring.taskflow.domain.dto.tasks.*;
import com.spring.taskflow.domain.service.TaskService;
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
    public ResponseEntity<TaskCreateResponseDto<?>> createTaskAPI(@RequestBody TaskCreateRequestDto requestDto) {
        // 1. 헤더에서 토큰 추출

        // 2. 토큰 검증
        Long userId = 1L;

        TaskCreateResponseDto<?> responseDto = taskService.createTaskService(userId, requestDto);
        ResponseEntity<TaskCreateResponseDto<?>> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    /**
     * Task 조회 API
     */
    @GetMapping
    public ResponseEntity<TaskListResponseDto<Object>> getTaskLiskAPI(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        TaskListResponseDto<Object> responseDto = taskService.getTaskListService(page, size);
        ResponseEntity<TaskListResponseDto<Object>> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    /**
     * Task 단건 조회 API
     */
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskGetDetailResponseDto> getTaskDetailAPI(@PathVariable("taskId") Long taskId) {
        TaskGetDetailResponseDto responseDto = taskService.getTaskDetialService(taskId);
        ResponseEntity<TaskGetDetailResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    /**
     * Task 수정 API
     */
    @PatchMapping("/{taskId}")
    public ResponseEntity<TaskUpdateResponseDto> updateTaskAPI(
            @PathVariable("taskId") Long taskId,
            @RequestBody TaskUpdateRequestDto requestDto
    ) {
        TaskUpdateResponseDto responseDto = taskService.updateTaskService(taskId, requestDto);
        ResponseEntity<TaskUpdateResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    /**
     * Task 삭제 API
     */
    @DeleteMapping("/{taskId}")
    public ResponseEntity<TaskDeleteResponseDto> deleteTaskAPI(@PathVariable("taskId") Long taskId) {
        TaskDeleteResponseDto responseDto = taskService.deleteTaskService(taskId);
        ResponseEntity<TaskDeleteResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }
}
