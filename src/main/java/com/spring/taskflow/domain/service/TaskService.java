package com.spring.taskflow.domain.service;

import com.spring.taskflow.domain.dto.tasks.TaskCreateDto;
import com.spring.taskflow.domain.dto.tasks.TaskCreateRequestDto;
import com.spring.taskflow.domain.dto.tasks.TaskCreateResponseDto;
import com.spring.taskflow.domain.entity.Task;
import com.spring.taskflow.domain.entity.User;
import com.spring.taskflow.domain.repository.TaskRepository;
import com.spring.taskflow.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public TaskCreateResponseDto<?> createTaskService(TaskCreateRequestDto requestDto) {
        Optional<User> optionalUser = userRepository.findById(requestDto.getAssigneeId());
        if (optionalUser.isPresent()) {
            User assigneeUser = optionalUser.get();
            Task foundTask = new Task(assigneeUser, requestDto);
            taskRepository.save(foundTask);
            TaskCreateResponseDto<TaskCreateDto> responseDto = new TaskCreateResponseDto<>(true, "태스크 생성이 완료되었습니다.", new TaskCreateDto(foundTask));
            return  responseDto;
        } else {
            TaskCreateResponseDto<Object> responseDto = new TaskCreateResponseDto<>(false, "필수 입력값을 확인해주세요.", null);
            return responseDto;
        }
    }

}
