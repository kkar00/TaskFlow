package com.spring.taskflow.domain.service;

import com.spring.taskflow.common.ApiResponse;
import com.spring.taskflow.domain.dto.dashboard.DashboardDto;
import com.spring.taskflow.domain.dto.dashboard.DashboardResponseDto;
import com.spring.taskflow.domain.dto.dashboard.DashboardUserDto;
import com.spring.taskflow.domain.entity.Dashboard;
import com.spring.taskflow.domain.entity.User;
import com.spring.taskflow.domain.enumdata.Status;
import com.spring.taskflow.domain.repository.DashboardRepository;
import com.spring.taskflow.domain.repository.TaskRepository;
import com.spring.taskflow.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DashboardService {
    // 속성
    private final DashboardRepository dashboardRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    // 생성자
    public DashboardService(DashboardRepository dashboardRepository, UserRepository userRepository, TaskRepository taskRepository) {
        this.dashboardRepository = dashboardRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    // 기능

    /**
     * 대시보드 조회 API
     */
    public ApiResponse<DashboardResponseDto> getDashboardService() {
        long totalTask = taskRepository.countAll();
        long doneTasks = taskRepository.countByStatus(Status.DONE);
        long inProgressTasks = taskRepository.countByStatus(Status.IN_PROGRESS);
        long todoTasks = taskRepository.countByStatus(Status.TODO);
        long overDueTasks = taskRepository.countByDueDateBefore(LocalDateTime.now());


        DashboardResponseDto responseDto = new DashboardResponseDto(totalTask, doneTasks, inProgressTasks, todoTasks, overDueTasks);
        ApiResponse<DashboardResponseDto> response = new ApiResponse<>(true, "대쉬보드 조회가 완료되었습니다.", responseDto);
        return response;
    }

    /**
     * 유저 대시보드 조회 API
     */
//    public ApiResponse<List<DashboardUserDto>> getDashboardUserService(Long userId) throws IllegalAccessException {
//        // 조회
//        List<Dashboard> dashboardList = dashboardRepository.findByUserId(userId);
//        if(!dashboardList.isEmpty()) {
//            List<DashboardUserDto> dashboardDtoList = dashboardList.stream()
//                    .map(dashboard -> new DashboardUserDto(dashboard.getUserId(), dashboard.getTotalTask(), dashboard.getDoneTasks(),
//                            dashboard.getInProgressTasks(), dashboard.getTodoTasks(), dashboard.getOverdueTasks(),
//                            dashboard.getDoneTasksRatio())
//                    ).collect(Collectors.toList());
//
//            // 반환 dto
//            ApiResponse<List<DashboardUserDto>> responseUserDto = new ApiResponse<List<DashboardUserDto>>(true, "대쉬보드 조회가 완료되었습니다.", dashboardDtoList);
//
//            // 반환
//            return responseUserDto;
//        } else {
//            // 조회 권한 없을시 예외처리
//            throw new IllegalAccessException();
//        }
//    }
    public ApiResponse<DashboardResponseDto> getUserDashboardService(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        long totalTask = taskRepository.countByAssigneeId(user);
        long doneTasks = taskRepository.countByAssigneeIdAndStatus(user, Status.TODO);
        long isProgressTasks = taskRepository.countByAssigneeIdAndStatus(user, Status.IN_PROGRESS);
        long todoTasks = taskRepository.countByAssigneeIdAndStatus(user, Status.DONE);
        long overDueTasks = taskRepository.countByAssigneeIdAndDueDateBefore(user, LocalDateTime.now());

        DashboardResponseDto responseDto = new DashboardResponseDto(totalTask, doneTasks, isProgressTasks, todoTasks, overDueTasks);
        ApiResponse<DashboardResponseDto> response = new ApiResponse<>(true, "대쉬보드 조회가 완료되었습니다.", responseDto);
        return response;

    }



}

