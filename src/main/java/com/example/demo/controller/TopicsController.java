package com.example.demo.controller;

import com.example.demo.model.topic.Topic;
import com.example.demo.model.topic.TopicData;
import com.example.demo.model.topic.TopicId;
import com.example.demo.model.topic.TopicList;
import com.example.demo.model.user.UserId;
import com.example.demo.operation.TopicOperations;
import com.example.demo.service.TopicsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/topics")
@Tag(name = "Topic API", description = "Управление топиками")
public class TopicsController implements TopicOperations {
    private static final Logger LOG = LoggerFactory.getLogger(TopicsController.class);

    @Autowired
    private final TopicsService topicService;

    public TopicsController(TopicsService topicService) {
        this.topicService = topicService;
    }

    @Override
    public ResponseEntity<Topic> get(Long id) {
        CompletableFuture<Topic> completableFuture = topicService.findById(new TopicId(id));
        Topic topic = completableFuture.join();

        LOG.debug("Topic with id={} was found successfully", id);
        return ResponseEntity.ok(topic);
    }

    @Override
    public ResponseEntity<Topic> create(TopicData topicData) {
        Topic topic = topicService.create(new Topic(
                new TopicId(null),
                topicData.description(),
                new UserId(topicData.userId())
            ));

        LOG.debug("Topic with id={} was created successfully", topic.getId());
        return ResponseEntity.ok(topic);
    }

    @Override
    public ResponseEntity<String> deleteTopic(Long userId, Long topicId) {
        topicService.delete(new UserId(userId), new TopicId(topicId));
        LOG.debug("Topic with id={} was deleted successfully", topicId);
        return ResponseEntity.ok("Topic deleted!");
    }

    @Override
    public ResponseEntity<TopicList> getUserTopics(Long userId) {
        List<Topic> topics = topicService.getUserTopics(userId);
        LOG.debug("Topics for user with id={} were got successfully", userId);
        return ResponseEntity.ok(new TopicList(topics));
    }
}
