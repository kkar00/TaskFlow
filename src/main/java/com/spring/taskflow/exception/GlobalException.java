package com.spring.taskflow.exception;

import com.spring.taskflow.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    // request body가 null일 때 예외 처리
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {

        ApiResponse<Void> apiResponse = new ApiResponse<>(false, "body는 null일 수 없습니다.", null);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    // Validation 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        ApiResponse<Void> apiResponse = new ApiResponse<>(false, ex.getMessage(), null);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    // UserException 예외 처리
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ApiResponse<Void>> handleUserException(UserException ex) {

        ApiResponse<Void> apiResponse = new ApiResponse<>(false, ex.getMessage(), null);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

}
