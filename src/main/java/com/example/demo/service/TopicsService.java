package com.example.demo.service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.topic.Topic;
import com.example.demo.model.topic.TopicData;
import com.example.demo.model.topic.TopicId;
import com.example.demo.exception.TopicNotFoundException;
import com.example.demo.repository.TopicsRepository;
import com.example.demo.model.user.UserId;
import com.example.demo.repository.UsersRepository;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RateLimiter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
@AllArgsConstructor
public class TopicsService {
    private final TopicsRepository topicRepository;
    private final UsersRepository userRepository;
    private final CircuitBreaker breaker = CircuitBreaker.ofDefaults("apiCircuitBreaker");
    private final RateLimiter rateLimiter = RateLimiter.ofDefaults("apiRateLimiter");

    private final Set<Topic> processTopics = ConcurrentHashMap.newKeySet();

    @Transactional(readOnly = true)
    public Topic findById(Long topicId) {
        return breaker.executeSupplier(
            () -> rateLimiter.executeSupplier(
                () -> topicRepository
                    .findById(topicId)
                    .orElseThrow(() -> new TopicNotFoundException(new TopicId(topicId))))
            );
    }

    /**
     *
     * @param topicData - topic view
     * @return created topic
     */
    @Transactional()
    public Topic create(TopicData topicData) {
      Topic topic = new Topic();
      topic.setDescription(topicData.description());
      topic.setUser(
          userRepository
              .findById(topicData.userId())
              .orElseThrow(() -> new UserNotFoundException(new UserId(topicData.userId()))
              )
      );
      topicRepository.save(topic);

      return topic;
    }

    @Transactional()
    public void delete(Long topicId) {
      topicRepository.deleteById(topicId);
    }
}