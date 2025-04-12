package com.example.homework.exception;

import com.example.homework.model.topic.TopicId;

public class TopicAlreadyExistsException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Website with id=%s description=%s already exists";

    public TopicAlreadyExistsException(final TopicId topicId, final String description) {
        super(String.format(DEFAULT_MESSAGE, topicId.getId(), description));
    }
}
