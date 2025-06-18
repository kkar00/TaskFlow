package com.spring.taskflow.domain.repository;

import com.spring.taskflow.domain.entity.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DashboardRepository extends JpaRepository<Dashboard, Long>{
}
