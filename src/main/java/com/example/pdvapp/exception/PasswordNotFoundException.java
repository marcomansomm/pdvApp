package com.example.pdvapp.exception;

public class PasswordNotFoundException extends RuntimeException {
    public PasswordNotFoundException(String message) {
        super(message);
    }
}
