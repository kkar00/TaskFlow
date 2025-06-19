package com.spring.taskflow.domain.controller;

import com.spring.taskflow.common.ApiResponse;
import com.spring.taskflow.domain.dto.user.login.LoginRequestDto;
import com.spring.taskflow.domain.dto.user.login.LoginResponseDto;
import com.spring.taskflow.domain.dto.user.signup.SignUpRequestDto;
import com.spring.taskflow.domain.dto.user.signup.SignUpResponseDto;
import com.spring.taskflow.domain.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.spring.taskflow.domain.service.JwtService;
import com.spring.taskflow.domain.dto.user.deleteUser.UserDeleteRequestDto;
import io.jsonwebtoken.JwtException;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignUpResponseDto>> signup(@Validated @RequestBody SignUpRequestDto request) {

        SignUpResponseDto signUpResponseDto = userService.signup(request);
        ApiResponse response = new ApiResponse(true, "회원가입이 완료되었습니다.", signUpResponseDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDto>> login(@RequestBody LoginRequestDto request) {

        LoginResponseDto loginResponseDto = userService.login(request);
        ApiResponse response = new ApiResponse(true, "로그인이 완료되었습니다.", loginResponseDto);

        // 3. 토큰 반환
        return new ResponseEntity<>(response, HttpStatus.CREATED);

        // TODO @Validated 추가
    }

    // 회원 탈퇴
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestHeader("Authorization") String authHeader, @RequestBody UserDeleteRequestDto requestDto) {
        try {
            String token = authHeader.replace("Bearer ", "");
            long userId = jwtService.verifyToken(token);

            userService.deleteUser(userId, requestDto.getUserEmail(), requestDto.getPassword());
            // 성공 응답
            return ResponseEntity.ok(new ApiResponse<>(true, "회원탈퇴가 완료되었습니다.", null));
        } catch (JwtException e) {
            // 인증 실패
            return ResponseEntity.status(401).body(new ApiResponse<>(false,"로그인이 필요합니다.", null));
        } catch (IllegalArgumentException e) {
            // 비밀번호 불일치, 이메일 불일치 등
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }
}
