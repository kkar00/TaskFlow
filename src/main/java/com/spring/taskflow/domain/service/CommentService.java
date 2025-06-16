package com.spring.taskflow.domain.service;

import com.spring.taskflow.domain.dto.comments.CommentCreateRequestDto;
import com.spring.taskflow.domain.dto.comments.CommentCreateResponseDto;
import com.spring.taskflow.domain.entity.Comment;
import com.spring.taskflow.domain.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
    //속성
    private final CommentRepository commentRepository;
    //생성자
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    //기능

    /**
     * 댓글 생성 기능
     */
    @Transactional
    public CommentCreateResponseDto createCommentService(CommentCreateRequestDto requestDto) {
        Comment foundComment = new Comment(requestDto);
        Comment saveComment = commentRepository.save(foundComment);
        CommentCreateResponseDto responseDto = new CommentCreateResponseDto(saveComment);
        return responseDto;

    }
}
