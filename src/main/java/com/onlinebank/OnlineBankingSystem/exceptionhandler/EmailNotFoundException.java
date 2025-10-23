package com.onlinebank.OnlineBankingSystem.exceptionhandler;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException(String message) {
        super(message);
    }
}
