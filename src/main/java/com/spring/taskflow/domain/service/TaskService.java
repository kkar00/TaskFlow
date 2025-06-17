package com.spring.taskflow.domain.service;

import com.spring.taskflow.domain.dto.tasks.*;
import com.spring.taskflow.domain.entity.Task;
import com.spring.taskflow.domain.entity.User;
import com.spring.taskflow.domain.repository.TaskRepository;
import com.spring.taskflow.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
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
     * Task 작성 기능
     */
    @Transactional
    public TaskCreateResponseDto<?> createTaskService(Long userId, TaskCreateRequestDto requestDto) {
        User loginUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("로그인 후 이용 가능합니다."));


        Optional<User> optionalUser = userRepository.findById(requestDto.getAssigneeId());
        if (optionalUser.isPresent()) {
            User assigneeUser = optionalUser.get();
            Task foundTask = new Task(loginUser, assigneeUser, requestDto);
            taskRepository.save(foundTask);
            TaskCreateResponseDto<TaskCreateDto> responseDto = new TaskCreateResponseDto<>(true, "태스크 생성이 완료되었습니다.", new TaskCreateDto(foundTask));
            return  responseDto;
        } else {
            TaskCreateResponseDto<Object> responseDto = new TaskCreateResponseDto<>(false, "필수 입력값을 확인해주세요.", null);
            return responseDto;
        }
    }

    /**
     * Task 조회 기능
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

    /**
     * Task 단건 조회 기능
     */
    public TaskGetDetailResponseDto getTaskDetialService(Long taskId) {
        Task foundTask = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("태스크가 존재하지 않습니다."));
        TaskGetDetailResponseDto responseDto = new TaskGetDetailResponseDto(true, "태스크 조회가 완료되었습니다.", new TaskGetDetailDto(foundTask));
        return responseDto;
    }

    /**
     * Task 수정 기능
     */
    @Transactional
    public TaskUpdateResponseDto updateTaskService(Long taskId, TaskUpdateRequestDto requestDto) {
        // 태스크 조회
        Task foundTask = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("태스크가 존재하지 않습니다."));
        // 담당자 조회
        User assignee = userRepository.findById(requestDto.getAssigneeId()).orElseThrow(() -> new RuntimeException("해당 담당자가 존재하지 않습니다"));
        // 태스크 업데이트
        foundTask.updateTask(assignee, requestDto);
        TaskUpdateResponseDto responseDto = new TaskUpdateResponseDto(true, "태스크 수정이 완료되었습니다.", new TaskUpdateDto(foundTask));
        return responseDto;

    }
}
