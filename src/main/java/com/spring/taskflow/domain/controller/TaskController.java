package com.spring.taskflow.domain.controller;

import com.spring.taskflow.domain.dto.tasks.TaskCreateDto;
import com.spring.taskflow.domain.dto.tasks.TaskCreateRequestDto;
import com.spring.taskflow.domain.dto.tasks.TaskCreateResponseDto;
import com.spring.taskflow.domain.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<TaskCreateResponseDto<TaskCreateDto>> createTaskAPI(@RequestBody TaskCreateRequestDto requestDto) {
        TaskCreateResponseDto<TaskCreateDto> responseDto = taskService.createTaskService(requestDto);
        ResponseEntity<TaskCreateResponseDto<TaskCreateDto>> response = new ResponseEntity<TaskCreateResponseDto<TaskCreateDto>>(responseDto, HttpStatus.OK);
        return response;
    }
}
