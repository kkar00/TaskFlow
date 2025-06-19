package com.spring.taskflow.domain.enumdata;

import com.spring.taskflow.exception.UserException;

public enum Role {
    USER,
    ADMIN;

    // 요청받은 string타입의 role을 enum타입으로 변환하는 메서드
    public static Role stringToRole(String stringRole) {
        try {
            return Role.valueOf(stringRole.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new UserException("admin, user 중 입력해야 합니다.");
        }
    }

}
