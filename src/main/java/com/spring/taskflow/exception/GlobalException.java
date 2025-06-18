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
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalException {

    // request body가 null일 때 예외 처리
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {

        ApiResponse<Void> apiResponse = new ApiResponse<>(false, "body는 null일 수 없습니다.", null);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

//    // Validation 예외 처리
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
//
//        ApiResponse<Void> apiResponse = new ApiResponse<>(false, ex.getMessage(), null);
//        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
//    }

    // UserException 예외 처리
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ApiResponse<Void>> handleUserException(UserException ex) {

        ApiResponse<Void> apiResponse = new ApiResponse<>(false, ex.getMessage(), null);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * RuntimeException, IllegalArgumentException 오류응답을 ApiResponse message에 넣어 Response함
     */
    @ExceptionHandler({RuntimeException.class, IllegalArgumentException.class})
    public ResponseEntity<ApiResponse<?>> handleRuntimeException(Exception e) {
        ApiResponse<?> errorResponse = new ApiResponse<>(false, e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * RequestDto NotBlack or NotNull 오류 발생시 메세지를 ApiResponse message 에 넣어 Response함
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ApiResponse<Object> response = new ApiResponse<>(false, message, null);
        ResponseEntity<ApiResponse<Object>> responseEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
}
