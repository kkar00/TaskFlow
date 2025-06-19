package com.spring.taskflow.domain.service;

import com.spring.taskflow.common.ApiResponse;
import com.spring.taskflow.domain.dto.tasks.*;
import com.spring.taskflow.domain.entity.ActivityLog;
import com.spring.taskflow.domain.entity.Task;
import com.spring.taskflow.domain.entity.User;
import com.spring.taskflow.domain.repository.ActivityLogRepository;
import com.spring.taskflow.domain.repository.TaskRepository;
import com.spring.taskflow.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    // 속성
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ActivityLogRepository activityLogRepository;

    // 생성자
    public TaskService(TaskRepository taskRepository, UserRepository userRepository, ActivityLogRepository activityLogRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.activityLogRepository = activityLogRepository;
    }


    // 기능

    /**
     * Task 작성 기능
     */
    @Transactional
    public ApiResponse<TaskCreateResponseDto> createTaskService(Long userId, TaskCreateRequestDto requestDto) {
        // 토큰으로 접속한 유저 확인
        User loginUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("회원 정보가 일치하지 않습니다."));

        // assignee null 확인
        Long assigneeID = requestDto.getAssigneeId();
        User assigneeUser = null;
        if (assigneeID != null) {
            assigneeUser = userRepository.findById(assigneeID).orElseThrow(() -> new IllegalArgumentException("담장자를 확인해주세요."));
        }

        // Task 저장
        Task foundTask = new Task(loginUser, assigneeUser, requestDto);
        taskRepository.save(foundTask);

        // 예: 활동 로그 저장
        ActivityLog log = new ActivityLog();
        log.setActivityType("CREATE");
        log.setTaskId(foundTask.getTaskId());
        log.setUserId(userId);
        log.setRequestedAt(LocalDateTime.now());
        log.setIpAddress("127.0.0.1");
        //IP는 예시
        log.setRequestMethod("POST");
        log.setRequestUrl("/api/tasks");
        activityLogRepository.save(log);

        // ResponseDto 생성 및 반환
        ApiResponse<TaskCreateResponseDto> responseDto = new ApiResponse<>(true, "태스크 생성이 완료되었습니다.", new TaskCreateResponseDto(foundTask));
        return responseDto;
    }

    /**
     * Task 조회 기능
     */
    public ApiResponse<TaskListDto> getTaskListService(int page , int size) {
        // 페이지 설정 ( createdAt 기준 내림차순 )
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Task> taskPage = taskRepository.findAllByIsDeletedFalse(pageable);

        List<TaskListResponseDto> responseDtoList = taskPage.getContent().stream()
                .map(TaskListResponseDto::new)
                .collect(Collectors.toList());

        ApiResponse<TaskListDto> response = new ApiResponse<>(true, "태스크 조회가 완료되었습니다.", new TaskListDto(responseDtoList));
        return response;
    }

    /**
     * Task 단건 조회 기능
     */
    public ApiResponse<TaskGetDetailResponseDto> getTaskDetialService(Long taskId) {
        Task foundTask = taskRepository.findByTaskIdAndIsDeletedFalse(taskId).orElseThrow(() -> new RuntimeException("태스크가 존재하지 않거나 삭제된 상태입니다."));
        ApiResponse<TaskGetDetailResponseDto> response = new ApiResponse<>(true, "태스크 조회가 완료되었습니다.", new TaskGetDetailResponseDto(foundTask));
        return response;
    }

    /**
     * Task 제목 검색 기능
     */
    public ApiResponse<TaskSearchListDto> getTaskSearchTitleService(int page , int size , TaskSearchListRequestDto requestDto) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Task> tasks = taskRepository.findByTitleContaining(requestDto.getSearch(), pageable);

        List<TaskSearchListResponseDto> responseDtoList = tasks.getContent().stream()
                .map(TaskSearchListResponseDto::new)
                .collect(Collectors.toList());

        ApiResponse<TaskSearchListDto> response = new ApiResponse<>(true, "태스그 검색이 완료되었습니다.", new TaskSearchListDto(responseDtoList));
        return response;
    }

    /**
     * Task 내용 검색 기능
     */
    public ApiResponse<TaskSearchListDto> getTaskSearchDescriptionService(int page , int size , TaskSearchListRequestDto requestDto) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Task> tasks = taskRepository.findByDescriptionContaining(requestDto.getSearch(), pageable);

        List<TaskSearchListResponseDto> responseDtoList = tasks.getContent().stream()
                .map(TaskSearchListResponseDto::new)
                .collect(Collectors.toList());

        ApiResponse<TaskSearchListDto> response = new ApiResponse<>(true, "태스크 검색이 완료되었습니다.", new TaskSearchListDto(responseDtoList));
        return response;
    }

    /**
     * Task 수정 기능
     */
    @Transactional()
    public ApiResponse<TaskUpdateResponseDto> updateTaskService(Long userId, Long taskId, TaskUpdateRequestDto requestDto) {
        // 토큰으로 접속한 유저 검증
        User loginUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("회원 정보가 일치하지 않습니다."));
        // 태스크 조회
        Task foundTask = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("태스크가 존재하지 않습니다."));
        // 담당자 조회
        User assignee = userRepository.findById(requestDto.getAssigneeId()).orElseThrow(() -> new RuntimeException("해당 담당자가 존재하지 않습니다"));
        // 태스크 상태 업데이트
        if (!foundTask.getStatus().transitionTo(requestDto.getStatus())) {
            throw new IllegalArgumentException("상태값은 순서에 맞게 변경해야 합니다.");
        }
        // 태스크 업데이트
        foundTask.updateTask(loginUser, assignee, requestDto);
        ApiResponse<TaskUpdateResponseDto> response = new ApiResponse<>(true, "태스크 수정이 완료되었습니다.", new TaskUpdateResponseDto(foundTask));
        return response;
    }

    /**
     * Task Status 수정 기능
     */
    @Transactional
    public ApiResponse<TaskUpdateStatusResponseDto> updateTaskStatusService(Long userId, Long taskId, TaskUpdateStatusRequestDto requestDto) {
        // 토큰으로 접속한 유저 검증
        User loginUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("회원 정보가 일치하지 않습니다."));
        // 태스크 조회
        Task foundTask = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("태스크가 존재하지 않습니다."));
        // 태스크 상태 업데이트
        if (!foundTask.getStatus().transitionTo(requestDto.getStatus())) {
            throw new IllegalArgumentException("상태값은 순서에 맞게 변경해야 합니다.");
        }
        foundTask.updateStatusTask(requestDto);
        ApiResponse<TaskUpdateStatusResponseDto> response = new ApiResponse<>(true, "태스크 상태값 수정이 완료되었습니다.", new TaskUpdateStatusResponseDto(foundTask));
        return response;
    }

    /**
     * Task 삭제 기능
     */
    @Transactional
    public ApiResponse<Object> deleteTaskService(Long userId, Long taskId) {
        // 토큰으로 접속한 유저 검증
        User loginUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("회원 정보가 일치하지 않습니다."));
        // 태스크 조회
        Task foundTask = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("태스크가 존재하지 않습니다."));
        // 태스크 삭제
        foundTask.deleteTask();
        ApiResponse<Object> response = new ApiResponse<>(true, "태스크 삭제가 완료되었습니다.",null);
        return response;
    }

}
