package com.spring.taskflow.domain.service;

import com.spring.taskflow.domain.entity.User;
import com.spring.taskflow.domain.repository.UserRepository;

public class UserService {

    /**
     * 회원 탈퇴 기능
     */
    public void deleteUser(String userEmail, String password) {
        // 이메일로 사용자 검색(없으면 예외 발생)
        User user = UserRepository.findByEmail(email).orEleseThrow(() -> new IllegalArgumentException("해당 이메일의 사용자를 찾을 수 없습니다."));
        // 비밀번호 검증(틀리면 예외 발생)
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        UserRepository.delete(user);
    }
}
