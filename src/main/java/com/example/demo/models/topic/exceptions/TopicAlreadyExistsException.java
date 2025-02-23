package com.example.demo.models.topic.exceptions;

import com.example.demo.models.topic.TopicId;

public class TopicAlreadyExistsException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Website with id=%s description=%s already exists";

    public TopicAlreadyExistsException(final TopicId topicId, final String description) {
        super(String.format(DEFAULT_MESSAGE, topicId.getId(), description));
    }
}
