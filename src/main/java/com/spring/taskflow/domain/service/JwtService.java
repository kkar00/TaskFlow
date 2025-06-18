package com.spring.taskflow.domain.service;

import com.spring.taskflow.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    /** 1. 비밀키 만들기
        2. 토큰 만들기
        3. 토큰을 검증하고 memberId를 반환 **/

    // 비밀키의 경우 일반적으로 환경변수로 관리합니다.
    // 편의를 위해 속성으로 선언해 놓고 사용하는 예시입니다.
    private String secret = "a-string-secret-at-least-256-bits-long";

    /**
     * 토큰 만들기
     */
    public String createJwtToken(User user) {

        // 1. 서명 만들기
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());

        // 2. 데이터 준비
        String subject = user.getUserId().toString(); // 사용자 준비
        Date now = new Date();                // 현재시간
        Date expiration = new Date(now.getTime() + 1000 * 60); // 만료시간 설정 1분뒤

        // 3. 토큰 만들기
        String jwt = Jwts.builder()
                .subject(subject)  // --payload 시작--
                .issuedAt(now)
                .claim("role", user.getRole().toString())
                .expiration(expiration)
                .signWith(secretKey)  // --payload 끝--
                .compact();
        return jwt;
    }

    /**
     * 토큰을 검증하고 memberId 를 반환합니다.
     */
    public long verifyToken(String token) {
        // 1. 서명 만들기
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());

        // 2. 검증
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token) // 검증 실패라면 여기서 예외 발생 -> 처리해 주기
                .getPayload();

        // 2. 사용자 추출
        String subject = claims.getSubject();
        // String value1  = (String) claims.get("key1"); // 커스텀하게 설정한 요소 추출

        // 3. 타입 변환
        long userId = Long.parseLong(subject);

        // 4. 반환
        return userId;
    }
}
