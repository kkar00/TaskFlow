package com.spring.taskflow.domain.repository;

import com.spring.taskflow.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
