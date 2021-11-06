package com.tahadonuk.urlshortener.exception;

public class UserConflictException extends ConflictException {
    public UserConflictException(String message) {
        super(message);
    }
}
