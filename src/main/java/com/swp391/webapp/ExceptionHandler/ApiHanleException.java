package com.swp391.webapp.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiHanleException {
    @ExceptionHandler(AlreadyExistedException.class)
    public ResponseEntity<?> alreadyExist(AlreadyExistedException exception) {
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.ALREADY_REPORTED);
    }
}
