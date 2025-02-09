package com.example.demo.models.website.exceptions;

import com.example.demo.models.website.WebsiteId;

public final class WebsiteNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Website with id=%s does not exist";

    public WebsiteNotFoundException(final WebsiteId websiteId) {
        super(String.format(DEFAULT_MESSAGE, websiteId));
    }
}

