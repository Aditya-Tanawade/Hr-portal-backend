package com.finalproject.hrportal.advices;

import com.finalproject.hrportal.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException exception){
        List<String>subError=new ArrayList<>();
        subError.add(exception.getMessage());
        ApiError apiError=ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .messgae("Resource Not Found")
                .subErrors(subError)
                .dateTime(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleInputValidation(MethodArgumentNotValidException exception){

       List<String> errors= exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error->error.getDefaultMessage())
                .collect(Collectors.toList());
        ApiError apiError=ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .messgae("Input Validation Failed")
                .subErrors(errors)
                .dateTime(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleInternalServer(Exception exception){
        ApiError apiError=ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .messgae(exception.getLocalizedMessage())
                .dateTime(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
