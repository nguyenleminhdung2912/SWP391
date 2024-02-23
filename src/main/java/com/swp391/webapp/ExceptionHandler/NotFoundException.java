package com.swp391.webapp.ExceptionHandler;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
