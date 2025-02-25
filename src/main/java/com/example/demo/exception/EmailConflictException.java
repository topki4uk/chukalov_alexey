package com.example.demo.exception;

public class EmailConflictException extends RuntimeException {
    public EmailConflictException(final String message) {
        super(message);
    }
}
