package com.spring.taskflow.domain.controller;

import com.spring.taskflow.common.ApiResponse;
import com.spring.taskflow.domain.dto.user.login.LoginRequestDto;
import com.spring.taskflow.domain.dto.user.login.LoginResponseDto;
import com.spring.taskflow.domain.dto.user.signup.SignUpRequestDto;
import com.spring.taskflow.domain.dto.user.signup.SignUpResponseDto;
import com.spring.taskflow.domain.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignUpResponseDto>> signUp(@Validated @RequestBody SignUpRequestDto request) {

        SignUpResponseDto signUpResponseDto = userService.signUp(request);
        ApiResponse response = new ApiResponse(true, "회원가입이 완료되었습니다.", signUpResponseDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDto>> login(@RequestBody LoginRequestDto requestDto) {
        throw new EntityNotFoundException();
    }
}
