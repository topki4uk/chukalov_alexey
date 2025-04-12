package com.example.homework.exception;

public class EmailConflictException extends RuntimeException {
    public EmailConflictException(final String message) {
        super(message);
    }
}
