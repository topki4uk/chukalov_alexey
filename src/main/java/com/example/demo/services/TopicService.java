package com.example.demo.services;

import com.example.demo.models.topic.Topic;
import com.example.demo.models.topic.TopicId;
import com.example.demo.models.topic.repositories.TopicRepository;
import com.example.demo.models.user.UserId;
import com.example.demo.models.user.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class TopicService {
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    public TopicService(TopicRepository topicRepository, UserRepository userRepository) {
        this.topicRepository = topicRepository;
      this.userRepository = userRepository;
    }

    public Topic findById(TopicId topicId) {
        return topicRepository.findById(topicId);
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
