package com.example.demo.exception;

import com.example.demo.model.user.UserId;

public final class UserNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "User with id=%s does not exist";

    public UserNotFoundException(final UserId userId) {
        super(String.format(DEFAULT_MESSAGE, userId));
    }
}
