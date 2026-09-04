package com.example.employeemanagementSystem.exceptionHandler;

import com.example.employeemanagementSystem.authcontroller.AuthController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleNotfound(ResourceNotFoundException ex){
        return buildResponse(HttpStatus.NOT_FOUND,ex.getMessage());
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String,Object>> handleAuth(AuthenticationException ex){
        return buildResponse(HttpStatus.UNAUTHORIZED,ex.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleMethodError(MethodArgumentNotValidException ex){
        String message=ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(err->err.getField()+": "+err.getDefaultMessage())
                .orElse("Validation Field");
        return buildResponse(HttpStatus.BAD_REQUEST,message);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleExcep(Exception ex){
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage());
    }
    private ResponseEntity<Map<String,Object>> buildResponse(HttpStatus status,String message){
        Map<String,Object> body=new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status",status.value());
        body.put("error",message);
        return new ResponseEntity<>(body,status);


    }

}
