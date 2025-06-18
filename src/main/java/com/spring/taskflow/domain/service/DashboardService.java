package com.spring.taskflow.domain.service;

import com.spring.taskflow.domain.common.ApiResponse;
import com.spring.taskflow.domain.dto.dashboard.DashboardDto;
import com.spring.taskflow.domain.dto.dashboard.DashboardUserDto;
import com.spring.taskflow.domain.entity.Dashboard;
import com.spring.taskflow.domain.repository.DashboardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    // 속성
    private final DashboardRepository dashboardRepository;

    // 생성자
    public DashboardService(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    // 기능

    /**
     * 대시보드 조회 API
     */
    public ApiResponse<List<DashboardDto>> getDashboardListService() {
        // 조회
        List<Dashboard> dashboardList = dashboardRepository.findAll();

        List<DashboardDto> dashboardDtoList = dashboardList.stream()
                .map(dashboard -> new DashboardDto(dashboard.getTotalTask(), dashboard.getDoneTasks(),
                        dashboard.getInProgressTasks(), dashboard.getTodoTasks(), dashboard.getOverdueTasks(),
                        dashboard.getDoneTasksRatio())
                ).collect(Collectors.toList());

        // 반환 dto
        ApiResponse<List<DashboardDto>> responseListDto = new ApiResponse<List<DashboardDto>>(true, "대쉬보드 조회가 완료되었습니다.", dashboardDtoList);

        // 반환
        return responseListDto;

    }
    /**
     * 유저 대시보드 조회 API
     */
    public ApiResponse<List<DashboardUserDto>> getDashboardUserService() throws IllegalAccessException {
        // 조회
        List<Dashboard> dashboardList = dashboardRepository.findAll();
        if(!dashboardList.isEmpty()) {
            List<DashboardUserDto> dashboardDtoList = dashboardList.stream()
                    .map(dashboard -> new DashboardUserDto(dashboard.getUserId(), dashboard.getTotalTask(), dashboard.getDoneTasks(),
                            dashboard.getInProgressTasks(), dashboard.getTodoTasks(), dashboard.getOverdueTasks(),
                            dashboard.getDoneTasksRatio())
                    ).collect(Collectors.toList());

            // 반환 dto
            ApiResponse<List<DashboardUserDto>> responseUserDto = new ApiResponse<List<DashboardUserDto>>(true, "대쉬보드 조회가 완료되었습니다.", dashboardDtoList);

            // 반환
            return responseUserDto;
        } else {
            // 조회 권한 없을시 예외처리
            throw new IllegalAccessException();
        }
    }
}

