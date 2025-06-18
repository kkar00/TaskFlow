package com.spring.taskflow.domain.repository;

import com.spring.taskflow.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUserEmail(String email);
    Boolean existsByUsername(String userName);
    Optional<User> findByUsername(String userName);
}
