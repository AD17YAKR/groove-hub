package com.promptverse.auth.exception;

public class UserNotFoundException extends AuthException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
