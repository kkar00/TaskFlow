package com.spring.taskflow.domain.service;

import com.spring.taskflow.domain.dto.tasks.TaskCreateRequestDto;
import com.spring.taskflow.domain.entity.Task;
import com.spring.taskflow.domain.repository.TaskRepository;
import com.spring.taskflow.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    // 속성
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    // 생성자
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    // 기능

}
