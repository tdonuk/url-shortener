package com.tahadonuk.urlshortener.exception;

public class URLConflictException extends ConflictException {
    public URLConflictException(String message) {
        super(message);
    }
}
