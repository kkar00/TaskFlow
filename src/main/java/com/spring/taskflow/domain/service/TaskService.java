package com.spring.taskflow.domain.service;

import com.spring.taskflow.domain.dto.tasks.*;
import com.spring.taskflow.domain.entity.Task;
import com.spring.taskflow.domain.entity.User;
import com.spring.taskflow.domain.repository.TaskRepository;
import com.spring.taskflow.domain.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    /**
     * Task 작성 API
     */
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

    /**
     * Task 조회 API
     */
    public TaskListResponseDto<Object> getTaskListService(int page , int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Task> taskPage = taskRepository.findAll(pageable);

        List<TaskListDto> taskListDtoList = taskPage.getContent().stream()
                .map(TaskListDto::new)
                .collect(Collectors.toList());

        TaskListResponseDto<Object> responseDto = new TaskListResponseDto<>(true, "태스크 조회가 완료되었습니다.", new TaskList(taskListDtoList));
        return responseDto;
    }
}
