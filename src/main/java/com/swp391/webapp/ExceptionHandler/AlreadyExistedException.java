package com.swp391.webapp.ExceptionHandler;

public class AlreadyExistedException extends RuntimeException{
    public AlreadyExistedException(String message) {
        super(message);
    }
}
