package com.example.demo.service;

import com.example.demo.model.topic.Topic;
import com.example.demo.model.topic.TopicId;
import com.example.demo.exception.TopicNotFoundException;
import com.example.demo.repository.TopicsRepository;
import com.example.demo.model.user.UserId;
import com.example.demo.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Service
@AllArgsConstructor
public class TopicsService {
    private final TopicsRepository topicRepository;
    private final UsersRepository userRepository;

    private final Set<Topic> processTopics = ConcurrentHashMap.newKeySet();

    @Async
    public CompletableFuture<Topic> findById(TopicId topicId) {
        Topic topic = topicRepository
            .findById(topicId)
            .orElseThrow(() -> new TopicNotFoundException(topicId));

        return CompletableFuture.completedFuture(topic);
    }

    public List<Topic> getUserTopics(Long userId) {
        userRepository.findById(new UserId(userId));
        return topicRepository.getUserTopics(userId);
    }

    /**
     *
     * @param topic - topic view
     * @return created topic
     */
    public Topic create(Topic topic) {
        if (!processTopics.add(topic)) {
            return topicRepository.create(topic);
        }
        return topic;
    }

    public void delete(UserId userId, TopicId topicId) {
        topicRepository.delete(userId, topicId);
    }
}
