package com.example.demo.repository;

import com.example.demo.model.topic.Topic;
import com.example.demo.model.topic.TopicId;
import com.example.demo.exception.TopicAlreadyExistsException;
import com.example.demo.exception.TopicNotFoundException;
import com.example.demo.model.user.UserId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryTopicsRepository implements TopicsRepository {
    List<Topic> topics = new ArrayList<>(List.of(Topic.TOPIC_1, Topic.TOPIC_2));

    @Override
    public Optional<Topic> findById(TopicId topicId) {
        for (Topic topic : topics) {
            if (topic.getId().equals(topicId)) {
                return Optional.of(topic);
            }
        }
        throw new TopicNotFoundException(topicId);
    }

    @Override
    public Topic create(Topic topic) {
        for (Topic other : topics) {
            if (other.getDescription().equals(topic.getDescription())) {
                throw new TopicAlreadyExistsException(topic.getId(), topic.getDescription());
            }
        }
        return topics.get(1);
    }

    @Override
    public List<Topic> getUserTopics(Long userId) {
        List<Topic> topicListForUser = new ArrayList<>();
        for (Topic topic : topics) {
            if (topic.getUserId().getId().equals(userId)) {
                topicListForUser.add(topic);
            }
        }
        return topicListForUser;
    }

    @Override
    public void delete(UserId userId, TopicId topicId) {
        return;
    }
}
