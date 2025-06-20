package com.spring.taskflow.domain.repository;

import com.spring.taskflow.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 태스크별 댓글 조회 + 내용 Like 검색 + 최신순 정렬
    @Query("SELECT c FROM Comment c WHERE c.task.taskId = :taskId AND (:keyword IS NULL OR c.content LIKE CONCAT('%', :keyword, '%')) ORDER BY c.createdAt DESC")
    List<Comment> findCommentsByTaskIdAndKeyword(@Param("taskId") Long taskId, @Param("keyword") String keyword);
}
