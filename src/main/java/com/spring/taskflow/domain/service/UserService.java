package com.spring.taskflow.domain.service;

import com.spring.taskflow.config.PasswordEncoder;
import com.spring.taskflow.domain.dto.user.login.LoginRequestDto;
import com.spring.taskflow.domain.dto.user.login.LoginResponseDto;
import com.spring.taskflow.domain.dto.user.signup.SignUpRequestDto;
import com.spring.taskflow.domain.dto.user.signup.SignUpResponseDto;
import com.spring.taskflow.domain.entity.User;
import com.spring.taskflow.domain.enumdata.Role;
import com.spring.taskflow.domain.repository.UserRepository;
import com.spring.taskflow.exception.UserException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public SignUpResponseDto signup(SignUpRequestDto request) {

        // 1. request에서 email 받기
        String email = request.getUserEmail();
        String userName = request.getUserName();
        // 2. email, userName이 존재하는지 확인 (repository)
        // 3. 존재하면 에러처리
        if(userRepository.existsByUserEmail(email)) {
            throw new UserException("이미 존재하는 이메일입니다.");
        }
        if(userRepository.existsByUserName(userName)) {
            throw new UserException("이미 존재하는 사용자명입니다.");
        }
        // 4. 존재하지 않으면
        // 5. request에서 password 꺼내서 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        // 6. User 생성 (email, 암호화된 pssword, name, role)
        Role role = Role.stringToRole(request.getRole());
        User user = new User(email, encodedPassword, request.getUserName(), role);
        // 7. repository에 저장 , savedUser변수에 저장
        User savedUser = userRepository.save(user);
        // 8. savedUser를 SignUpResponseDto로 변환 후 반환
        return new SignUpResponseDto(savedUser);
    }

    public LoginResponseDto login(LoginRequestDto request) {

        // 1. request에서 password, userName 받기 (비밀번호는 인코드)
        String password = request.getPassword();
        String userEmail = request.getUserEmail();
        // 2. 존재하는 회원인지 아이디, 비밀번호 확인
        // 3. 로그인 실패 - 예외처리
        User loginUser = userRepository.findByUserEmail(userEmail).orElseThrow(() -> new UserException("잘못된 이메일 입니다."));
        if(passwordEncoder.matches(password, loginUser.getPassword())) {
            // 4. 로그인 성공
            // 5. 토큰 발급, 반환
            String token = jwtService.createJwtToken(loginUser);
            return new LoginResponseDto(token);
        } else {
            throw new UserException("잘못된 비밀번호 입니다.");
        }
    }

    /**
     * 회원 탈퇴 기능
     */
    public void deleteUser(long userId, String userEmail, String password) {
        // 1. 사용자 조회
        Optional<User> optionalUser = userRepository.findById(userId);

        User user = optionalUser.get();

        // 2. 이메일 일치 확인
        if (!user.getUserEmail().equals(userEmail)) {
            throw new IllegalArgumentException("이메일이 일치하지 않습니다.");
        }

        // 3. 비밀번호 일치 확인
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 4. 회원 탈퇴
        userRepository.delete(user);
    }
}
