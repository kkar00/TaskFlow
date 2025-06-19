package com.spring.taskflow.domain.controller;

import com.spring.taskflow.common.ApiResponse;
import com.spring.taskflow.domain.dto.activitylog.ActivityLogErrorResponseDto;
import com.spring.taskflow.domain.entity.ActivityLog;
import com.spring.taskflow.domain.service.ActivityLogService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/activityLogs")
public class ActivityLogController {

    private final ActivityLogService activityLogService;

    public ActivityLogController(ActivityLogService activityLogService) {
        this.activityLogService = activityLogService;
    }

    @GetMapping
    public ResponseEntity<?> getFilteredActivityLogs(
            @RequestParam(required = false) Long taskId,
            @RequestParam(required = false) String activityType,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortOrder
    ) {
        try {
            List<ActivityLog> logs = activityLogService.getFilteredActivityLogs(taskId, activityType, startDate, endDate, sortBy, sortOrder);
            ApiResponse<List<?>> response = new ApiResponse<>(true, "활동로그 조회가 완료되었습니다.", logs);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            ActivityLogErrorResponseDto errorResponseDto = new ActivityLogErrorResponseDto(400, e.getMessage());
            return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
        }
    }
}
