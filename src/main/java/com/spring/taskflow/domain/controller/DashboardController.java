package com.spring.taskflow.domain.controller;

import com.spring.taskflow.common.ApiResponse;
import com.spring.taskflow.domain.dto.dashboard.DashboardDto;
import com.spring.taskflow.domain.dto.dashboard.DashboardUserDto;
import com.spring.taskflow.domain.service.DashboardService;
import com.spring.taskflow.domain.service.JwtService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
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
    private final JwtService jwtService;

    // 생성자
    public DashboardController(DashboardService dashboardService, JwtService jwtService) {
        this.dashboardService = dashboardService;
        this.jwtService = jwtService;
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

    /**
     * 유저 대시보드 조회 API
     */
    @GetMapping("/dashboards/{id}")
    public ResponseEntity<ApiResponse<List<DashboardUserDto>>> getDashboardUserAPI(
            HttpServletRequest request
    ){

        // 1. 헤더에서 토큰 추출
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7); // bearer 제거

        // 2. 토큰 검증
        Long userId = jwtService.verifyToken(token);

        try {
            ApiResponse<List<DashboardUserDto>> responseDashboardUserDto = dashboardService.getDashboardUserService(userId);
            ResponseEntity<ApiResponse<List<DashboardUserDto>>> response = new ResponseEntity<>(responseDashboardUserDto, HttpStatus.OK);
            return response;
        } catch (IllegalAccessException e) {
            return new ResponseEntity<>(
                    new ApiResponse<>(false,"조회 권한이 없습니다.", null),
                    HttpStatus.FORBIDDEN);
        }
    }
}
