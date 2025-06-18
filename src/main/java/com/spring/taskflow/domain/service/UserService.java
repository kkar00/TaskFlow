package com.spring.taskflow.domain.service;

import com.spring.taskflow.config.PasswordEncoder;
import com.spring.taskflow.domain.entity.User;
import com.spring.taskflow.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    // 속성
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 생성자
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 회원 탈퇴 기능
     */
    public void deleteUser(long userId, String userEmail, String password) {
        // 1. ID로 사용자 조회 (토큰이 유효하다는 가정 하에 userId는 DB에 존재해야 함)
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            // 이 상황은 토큰이 유효한데 userId로 조회 실패한 경우로, 발생하지 않아야 정상
            return; // 또는 log.warn(...)만 남김
        }

        User user = optionalUser.get();

        // 2. 이메일 일치 확인
        if (!user.getUserEmail().equals(userEmail)) {
            throw new IllegalArgumentException("이메일이 일치하지 않습니다.");
        }

        // 3. 비밀번호 일치 확인 (암호화된 비밀번호 비교)
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 4. 회원 탈퇴 (DB에서 삭제)
        userRepository.delete(user);
    }
}
