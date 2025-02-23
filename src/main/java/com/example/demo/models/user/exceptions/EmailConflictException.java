package com.example.demo.models.user.exceptions;

public class EmailConflictException extends RuntimeException {
    public EmailConflictException(final String message) {
        super(message);
    }
}
