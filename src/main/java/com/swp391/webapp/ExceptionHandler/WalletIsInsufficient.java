package com.swp391.webapp.ExceptionHandler;

public class WalletIsInsufficient extends RuntimeException{

    public WalletIsInsufficient(String message) {
        super(message);
    }

}
