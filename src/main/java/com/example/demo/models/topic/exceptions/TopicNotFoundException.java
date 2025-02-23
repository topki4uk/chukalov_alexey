package com.example.demo.models.topic.exceptions;

import com.example.demo.models.topic.TopicId;

public class TopicNotFoundException extends RuntimeException {
  private static final String DEFAULT_MESSAGE = "Topic with id=%s does not exist";

  public TopicNotFoundException(final TopicId topicId) {
    super(String.format(DEFAULT_MESSAGE, topicId.getId()));
  }
}
