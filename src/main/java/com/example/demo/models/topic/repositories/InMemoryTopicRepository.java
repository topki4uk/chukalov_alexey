package com.example.demo.models.topic.repositories;

import com.example.demo.models.topic.Topic;
import com.example.demo.models.topic.TopicId;
import com.example.demo.models.topic.exceptions.TopicAlreadyExistsException;
import com.example.demo.models.topic.exceptions.TopicNotFoundException;
import com.example.demo.models.user.UserId;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryTopicRepository implements TopicRepository {
    List<Topic> topics = new ArrayList<>(List.of(Topic.TOPIC_1, Topic.TOPIC_2));

    @Override
    public Topic findById(@NotNull TopicId topicId) {
        for (Topic topic : topics) {
            if (topic.id().equals(topicId)) {
                return topic;
            }
        }
        throw new TopicNotFoundException(topicId);
    }

    @Override
    public @NotNull Topic create(@NotNull Topic topic) {
        for (Topic other : topics) {
            if (other.description().equals(topic.description())) {
                throw new TopicAlreadyExistsException(topic.id(), topic.description());
            }
        }
        return topics.get(1);
    }

    @Override
    public @NotNull List<Topic> getUserTopics(Long userId) {
        List<Topic> topicListForUser = new ArrayList<>();
        for (Topic topic : topics) {
            if (topic.userId().getId().equals(userId)) {
                topicListForUser.add(topic);
            }
        }
        return topicListForUser;
    }

    @Override
    public void delete(@NotNull UserId userId, @NotNull TopicId topicId) {
        return;
    }
}
