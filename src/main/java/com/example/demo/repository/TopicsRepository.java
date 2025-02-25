package com.example.demo.repository;

import com.example.demo.model.topic.Topic;
import com.example.demo.model.topic.TopicId;
import com.example.demo.model.user.UserId;

import java.util.List;
import java.util.Optional;

public interface TopicsRepository {
    Optional<Topic> findById(TopicId topicId);

    Topic create(Topic topic);

    List<Topic> getUserTopics(Long userId);

    void delete(UserId userId, TopicId topicId);
}
