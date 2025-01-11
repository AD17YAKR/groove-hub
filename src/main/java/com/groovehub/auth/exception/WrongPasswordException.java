package com.groovehub.auth.exception;

public class WrongPasswordException extends AuthException {
    public WrongPasswordException(String message) {
        super(message);
    }
}