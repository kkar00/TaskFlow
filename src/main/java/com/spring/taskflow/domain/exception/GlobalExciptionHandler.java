package com.spring.taskflow.domain.exception;

import com.spring.taskflow.common.ApiResponse;
import com.spring.taskflow.domain.dto.tasks.TaskCreateResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExciptionHandler {
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

