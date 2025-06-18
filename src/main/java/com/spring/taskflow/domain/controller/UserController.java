package com.spring.taskflow.domain.controller;

import com.spring.taskflow.domain.dto.user.UserDeleteErrorResponseDto;
import com.spring.taskflow.domain.dto.user.UserDeleteResponseDto;
import com.spring.taskflow.domain.service.JwtService;
import com.spring.taskflow.domain.service.UserService;
import com.spring.taskflow.domain.dto.user.UserDeleteRequestDto;
import io.jsonwebtoken.JwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    // 속성
    private final UserService userService;
    private final JwtService jwtService;

    // 생성자
    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/api/users/delete")
    public ResponseEntity<?> deleteUser(@RequestHeader("Authorization") String authHeader, @RequestBody UserDeleteRequestDto requestDto) {
        try {
            String token = authHeader.replace("Bearer ", "");
            long userId = jwtService.verifyToken(token);
            userService.deleteUser(userId, requestDto.getUserEmail(), requestDto.getPassword());
            return ResponseEntity.ok(new UserDeleteResponseDto(200, "회원탈퇴가 완료되었습니다."));
        } catch (JwtException e) {
            return ResponseEntity.status(401).body(new UserDeleteErrorResponseDto(401, "로그인이 필요합니다."));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new UserDeleteErrorResponseDto(400, e.getMessage()));
        }
    }
}
