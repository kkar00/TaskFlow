package com.spring.taskflow.domain.service;

import com.spring.taskflow.domain.dto.comments.*;
import com.spring.taskflow.domain.entity.Comment;
import com.spring.taskflow.domain.entity.Task;
import com.spring.taskflow.domain.entity.User;
import com.spring.taskflow.domain.repository.CommentRepository;
import com.spring.taskflow.domain.repository.TaskRepository;
import com.spring.taskflow.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    //속성
    private final CommentRepository commentRepository;
    private UserRepository userRepository;
    private TaskRepository taskRepository;
    //생성자
    public CommentService(CommentRepository commentRepository, UserRepository userRepository, TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }
    //기능

    /**
     * 댓글 생성 기능
     */
    @Transactional
    public CommentCreateResponseDto createCommentService(CommentCreateRequestDto requestDto,Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        Task task = taskRepository.findById(requestDto.getTaskId())
                .orElseThrow(() -> new RuntimeException("작업(Task)을 찾을 수 없습니다."));
        Comment foundComment = new Comment(requestDto);
        foundComment.setContent(requestDto.getContent());
        foundComment.setUser(user);
        foundComment.setTask(task);
        foundComment.setUserName(user.getUserName());
        Comment saveComment = commentRepository.save(foundComment);
        CommentCreateResponseDto responseDto = new CommentCreateResponseDto(saveComment);
        return responseDto;
    }
    /**
     * 댓글 조회 기능
     */
    public List<CommentGetResponseDto> getComments(CommentGetRequestDto requestDto) {
        List<Comment> comments = commentRepository.findCommentsByTaskIdAndKeyword(
                requestDto.getTaskId(),
                requestDto.getKeyword()
        );

        return comments.stream()
                .map(CommentGetResponseDto::new)
                .collect(Collectors.toList());
    }
    /**
     * 댓글 단건 조회 기능
     */
    public CommentGetResponseDto getCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다."));
        return new CommentGetResponseDto(comment);
    }
    /**
     * 댓글 수정 기능
     */
    @Transactional
    public CommentUpdateResponseDto updateComment(Long commentId, CommentUpdateRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다."));

        comment.setContent(requestDto.getContent());  // 내용만 수정
        comment.setUpdatedAt(LocalDateTime.now());    // 수정 시간 갱신

        return new CommentUpdateResponseDto(comment);
    }

    /**
     * 댓글 삭제 기능
     */
    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다."));

        comment.setDeleted(true); //false를 true로 수정
        comment.setDeletedAt(LocalDateTime.now()); //삭제 시간 갱신

        commentRepository.save(comment);
    }
}
