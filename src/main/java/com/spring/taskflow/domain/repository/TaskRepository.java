package com.spring.taskflow.domain.repository;

import com.spring.taskflow.domain.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findAllByIsDeletedFalse(Pageable pageable);
}
