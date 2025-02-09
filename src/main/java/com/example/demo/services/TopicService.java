package com.example.demo.services;

import com.example.demo.models.topic.Topic;
import com.example.demo.models.topic.TopicId;
import com.example.demo.models.topic.repositories.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class TopicService {
    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Optional<Topic> findById(TopicId topicId) {
        return topicRepository.findById(topicId);
    }

    public Topic create(Topic topic) {
        return topicRepository.create(topic);
    }
}
