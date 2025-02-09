package com.example.demo.models.topic.repositories;

import com.example.demo.models.topic.Topic;
import com.example.demo.models.topic.TopicId;
import com.example.demo.models.topic.exceptions.TopicAlreadyExistsException;
import com.example.demo.models.topic.exceptions.TopicNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryTopicRepository implements TopicRepository {
    List<Topic> topics = new ArrayList<>(List.of(Topic.TOPIC_1, Topic.TOPIC_2));

    @Override
    public Optional<Topic> findById(@NotNull TopicId topicId) {
        for (Topic topic : topics) {
            if (topic.id().equals(topicId)) {
                return Optional.of(topic);
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
    public @NotNull List<Topic> getAll() {
        return topics;
    }

    @Override
    public void delete(@NotNull TopicId topicId) {

    }
}
