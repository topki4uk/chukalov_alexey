package com.example.demo.models.website.exceptions;

import com.example.demo.models.website.WebsiteId;

public class WebsiteAlreadyExistsException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Website with id=%s url=%s already exists";

    public WebsiteAlreadyExistsException(final WebsiteId websiteId, final String url) {
        super(String.format(DEFAULT_MESSAGE, websiteId, url));
    }
}
