package com.spring.taskflow.domain.controller;

import com.spring.taskflow.domain.common.ApiResponse;
import com.spring.taskflow.domain.dto.dashboard.DashboardDto;
import com.spring.taskflow.domain.service.DashboardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboards")
public class DashboardController {

    // 속성
    private final DashboardService dashboardService;

    // 생성자
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    // 기능
    /**
     * 대시보드 조회 API
     */
    @GetMapping("/dashboards")
    public ResponseEntity<ApiResponse<List<DashboardDto>>> getDashboardListAPI(){
        ApiResponse<List<DashboardDto>> responseDashboardListDto = dashboardService.getDashboardListService();
        ResponseEntity<ApiResponse<List<DashboardDto>>> response = new ResponseEntity<>(responseDashboardListDto, HttpStatus.OK);
        return response;
    }

}
