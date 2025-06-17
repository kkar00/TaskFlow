package com.spring.taskflow.domain.service;

import com.spring.taskflow.config.PasswordEncoder;
import com.spring.taskflow.domain.dto.user.signup.SignUpRequestDto;
import com.spring.taskflow.domain.dto.user.signup.SignUpResponseDto;
import com.spring.taskflow.domain.entity.User;
import com.spring.taskflow.domain.repository.UserRepository;
import com.spring.taskflow.exception.UserException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public SignUpResponseDto signUp(SignUpRequestDto request) {

        // 1. request에서 email 꺼내기
        String email = request.getUserEmail();
        // 2. email이 존재하는지 확인 (repository)
        // 3. 존재하면 에러처리
        if(userRepository.existsByUserEmail(email)) {
            throw new UserException("이미 존재하는 이메일입니다.");
        }
        // 4. 존재하지 않으면
        // 5. request에서 password 꺼내서 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        // 6. User 생성 (email, 암호화된 pssword, name, role)
        User user = new User(email, encodedPassword, request.getUserName(), "admin");
        // 7. repository에 저장 , savedUser변수에 저장
        User savedUser = userRepository.save(user);
        // 8. savedUser를 SignUpResponseDto로 변환 후 반환
        return new SignUpResponseDto(savedUser);
    }
}
