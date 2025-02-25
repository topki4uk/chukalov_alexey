package com.example.demo.exception;

import com.example.demo.model.topic.TopicId;

public class TopicNotFoundException extends RuntimeException {
  private static final String DEFAULT_MESSAGE = "Topic with id=%s does not exist";

  public TopicNotFoundException(final TopicId topicId) {
    super(String.format(DEFAULT_MESSAGE, topicId.getId()));
  }
}
