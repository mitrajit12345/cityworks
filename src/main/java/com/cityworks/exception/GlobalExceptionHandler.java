package com.cityworks.exception;
import com.cityworks.api.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<APIResponse<Void>> handleTaskNotFoundException(TaskNotFoundException tnfe){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(APIResponse.<Void>builder()
                .status("ERROR")
                .message(tnfe.getMessage())
                .data(null)
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<APIResponse<Map<String, String>>> handleValidationException(MethodArgumentNotValidException manv){
        Map<String, String> fieldErrors = new HashMap<>();

        manv.getBindingResult()
                .getFieldErrors()
                .forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage())
                );

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(APIResponse.<Map<String, String>>builder()
                        .status("Error")
                        .message("Validation failed")
                        .data(fieldErrors)
                        .build());
    }

}
