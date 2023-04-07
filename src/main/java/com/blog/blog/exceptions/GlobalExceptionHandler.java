package com.blog.blog.exceptions;

import com.blog.blog.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException exception){
        String message=exception.getMessage();
        ApiResponse apiResponse=new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException exception){
        Map<String ,String> resp=new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error)->{
            String fieldname=((FieldError) error).getField();
            String message=error.getDefaultMessage();
            resp.put(fieldname,message);
        });
        return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
    }
}
