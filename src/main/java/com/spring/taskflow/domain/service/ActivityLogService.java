package com.spring.taskflow.domain.service;

import com.spring.taskflow.domain.dto.activitylog.ActivityLogResponseDto;
import com.spring.taskflow.domain.entity.ActivityLog;
import com.spring.taskflow.domain.repository.ActivityLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ActivityLogService {
    // 속성
    private final ActivityLogRepository activityLogRepository;

    // 생성자
    public ActivityLogService(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    // 기능
    /**
     * 활동 로그 조히
     */
    public List<ActivityLog> getFilteredActivityLogs(
            Long taskId,
            String activityType,
            LocalDateTime startDate,
            LocalDateTime endDate,
            String sortBy,
            String sortOrder
    ) {
        // 조건이 모두 null인 경우
        if (taskId == null && activityType == null && startDate == null && endDate == null) {
            throw new IllegalArgumentException("필터 조건을 최소 한 개 이상 입력해주세요.");
        }
        // 조건별 필터링
        List<ActivityLog> logs = activityLogRepository.findAll();

        if (taskId != null) {
            logs = logs.stream().filter(log ->log.getTaskId() != null && log.getTaskId().equals(taskId)).collect(Collectors.toList());
        }
        if (activityType != null) {
            logs = logs.stream().filter(log -> log.getActivityType() != null && log.getActivityType().equals(activityType)).collect(Collectors.toList());
        }
        if (startDate != null && endDate != null) {
            if (startDate.isAfter(endDate)) {
                throw new IllegalArgumentException("시작일은 종료일 이전이거나 같아야 합니다.");
            }
            logs = logs.stream().filter(log -> log.getRequestedAt() != null
                    && !log.getRequestedAt().isBefore(startDate)
                    && !log.getRequestedAt().isAfter(endDate)).collect(Collectors.toList());
        } else if (startDate != null) {
            logs = logs.stream().filter(log -> log.getRequestedAt() != null && !log.getRequestedAt().isBefore(startDate)).collect(Collectors.toList());
        } else if (endDate != null) {
            logs = logs.stream().filter(log -> log.getRequestedAt() != null && !log.getRequestedAt().isAfter(endDate)).collect(Collectors.toList());
        }
        // 정렬 처리
        if (sortBy != null) {
            Comparator<ActivityLog> comparator = null;

            if ("requestedAt".equalsIgnoreCase(sortBy)) {
                comparator = Comparator.comparing(ActivityLog::getRequestedAt, Comparator.nullsLast(Comparator.naturalOrder()));
            } else if ("activityType".equalsIgnoreCase(sortBy)) {
                comparator = Comparator.comparing(ActivityLog::getActivityType, Comparator.nullsLast(Comparator.naturalOrder()));
            }
            if (comparator != null) {
                if ("desc".equalsIgnoreCase(sortOrder)) {
                    comparator = comparator.reversed();
                }
                logs = logs.stream().sorted(comparator).collect(Collectors.toList());
            }
        }
        return logs;
    }
    public List<ActivityLogResponseDto> getActivityLogs(
            Long taskId,
            String activityType,
            LocalDateTime startDate,
            LocalDateTime endDate,
            String sortBy,
            String sortOrder
    ) {
        List<ActivityLog> logs = getFilteredActivityLogs(taskId, activityType, startDate, endDate, sortBy, sortOrder);

        return logs.stream()
                .map(log -> new ActivityLogResponseDto(
                        log.getActivityLogId(),
                        log.getRequestedAt(),
                        log.getIpAddress(),
                        log.getRequestMethod(),
                        log.getRequestUrl(),
                        log.getUserId(),
                        log.getTaskId(),
                        log.getCommentId(),
                        log.getActivityType()
                ))
                .collect(Collectors.toList());
    }
}