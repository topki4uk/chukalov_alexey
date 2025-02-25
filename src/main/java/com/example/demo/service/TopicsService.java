package com.example.demo.service;

import com.example.demo.model.topic.Topic;
import com.example.demo.model.topic.TopicId;
import com.example.demo.exception.TopicNotFoundException;
import com.example.demo.repository.TopicsRepository;
import com.example.demo.model.user.UserId;
import com.example.demo.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public final class TopicsService {
    private final TopicsRepository topicRepository;
    private final UsersRepository userRepository;

    public Topic findById(TopicId topicId) {
        return topicRepository
            .findById(topicId)
            .orElseThrow(() -> new TopicNotFoundException(topicId));
    }

    public List<Topic> getUserTopics(Long userId) {
        userRepository.findById(new UserId(userId));
        return topicRepository.getUserTopics(userId);
    }

    public Topic create(Topic topic) {
        return topicRepository.create(topic);
    }

    public void delete(UserId userId, TopicId topicId) {
        topicRepository.delete(userId, topicId);
    }
}
