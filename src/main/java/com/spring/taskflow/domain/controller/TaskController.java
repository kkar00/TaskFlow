package com.spring.taskflow.domain.controller;

import com.spring.taskflow.domain.dto.tasks.TaskCreateDto;
import com.spring.taskflow.domain.dto.tasks.TaskCreateRequestDto;
import com.spring.taskflow.domain.dto.tasks.TaskCreateResponseDto;
import com.spring.taskflow.domain.dto.tasks.TaskListResponseDto;
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
        TaskCreateResponseDto<?> responseDto = taskService.createTaskService(requestDto);
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
}
