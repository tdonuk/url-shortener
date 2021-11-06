package com.tahadonuk.urlshortener.exception;

public class ConflictException extends Exception{
    public ConflictException(String message) {
        super(message);
    }
}
