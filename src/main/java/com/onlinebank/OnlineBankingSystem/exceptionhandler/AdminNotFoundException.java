package com.onlinebank.OnlineBankingSystem.exceptionhandler;

public class AdminNotFoundException extends RuntimeException {
    public AdminNotFoundException(String message) {
        super(message);
    }
}
