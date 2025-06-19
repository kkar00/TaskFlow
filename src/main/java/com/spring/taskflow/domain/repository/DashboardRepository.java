package com.spring.taskflow.domain.repository;

import com.spring.taskflow.domain.entity.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface DashboardRepository extends JpaRepository<Dashboard, Long>{
    List<Dashboard> findByUserId(Long userId);
}
