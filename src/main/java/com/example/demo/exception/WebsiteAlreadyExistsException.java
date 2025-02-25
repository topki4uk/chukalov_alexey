package com.example.demo.exception;

import com.example.demo.model.website.WebsiteId;

public class WebsiteAlreadyExistsException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Website with id=%s url=%s already exists";

    public WebsiteAlreadyExistsException(final WebsiteId websiteId, final String url) {
        super(String.format(DEFAULT_MESSAGE, websiteId.getId(), url));
    }
}
