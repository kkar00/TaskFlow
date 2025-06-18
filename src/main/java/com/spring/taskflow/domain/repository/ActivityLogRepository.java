package com.spring.taskflow.domain.repository;

import com.spring.taskflow.domain.entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
    List<ActivityLog> findByTaskId(Long taskId);
    List<ActivityLog> findByActivityType(String activityType);
    List<ActivityLog> findByRequestedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<ActivityLog> findByRequestedAtAfter(LocalDateTime start);
    List<ActivityLog> findByRequestedAtBefore(LocalDateTime end);
}
