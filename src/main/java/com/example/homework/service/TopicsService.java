package com.example.homework.service;

import com.example.homework.exception.UserNotFoundException;
import com.example.homework.model.topic.Topic;
import com.example.homework.model.topic.TopicData;
import com.example.homework.model.topic.TopicId;
import com.example.homework.exception.TopicNotFoundException;
import com.example.homework.repository.TopicsRepository;
import com.example.homework.model.user.UserId;
import com.example.homework.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TopicsService {
    private final TopicsRepository topicRepository;
    private final UsersRepository userRepository;

    @Transactional(readOnly = true)
    public Topic findById(Long topicId) {
        return topicRepository.findById(topicId)
            .orElseThrow(() -> new TopicNotFoundException(new TopicId(topicId)));
    }

    public List<Topic> getUserTopics(Long userId) {
        return null;
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
          userRepository.findById(topicData.userId())
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