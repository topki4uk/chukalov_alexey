package com.example.demo.models.website.exceptions;

import com.example.demo.models.user.UserId;

public class QuantityLimitExceededWebsitesPerUserException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "User with id=%s exceeded quantity limit websites";

    public QuantityLimitExceededWebsitesPerUserException(final UserId websiteId) {
        super(String.format(DEFAULT_MESSAGE, websiteId));
    }
}
