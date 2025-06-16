package com.spring.taskflow.domain.repository;

import com.spring.taskflow.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
