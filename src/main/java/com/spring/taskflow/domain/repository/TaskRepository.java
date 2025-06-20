package com.spring.taskflow.domain.repository;

import com.spring.taskflow.domain.entity.Task;
import com.spring.taskflow.domain.entity.User;
import com.spring.taskflow.domain.enumdata.Status;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findAllByIsDeletedFalse(Pageable pageable);
    Optional<Task> findByTaskIdAndIsDeletedFalse(Long taskId);
    Page<Task> findByTitleContaining(String keyword, Pageable pageable);
    Page<Task> findByDescriptionContaining(String keyword, Pageable pageable);

    // 대쉬보드 조회시 사용
    @Query("SELECT COUNT(e) FROM Task e")
    long countAll();
    long countByStatus(@Param("status") Status status);
    long countByDueDateBeforeAndStatusNot(LocalDateTime dateTime, Status status);

    // 대쉬보드에서 각 유저별 조회시 사용
    long countByAssigneeId(User user);
    long countByAssigneeIdAndStatus(User user, Status status);
    long countByAssigneeIdAndDueDateBeforeAndStatusNot(User user, LocalDateTime dateTime, Status status);
}
