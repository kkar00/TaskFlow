package com.spring.taskflow.domain.repository;

import com.spring.taskflow.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
