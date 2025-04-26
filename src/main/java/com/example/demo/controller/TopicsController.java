package com.example.demo.controller;

import com.example.demo.model.topic.Topic;
import com.example.demo.model.topic.TopicData;
import com.example.demo.model.topic.TopicList;
import com.example.demo.operation.TopicOperations;
import com.example.demo.service.TopicsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/topics")
@Tag(name = "Topic API", description = "Управление топиками")
public class TopicsController implements TopicOperations {
    private static final Logger LOG = LoggerFactory.getLogger(TopicsController.class);

    private final TopicsService topicService;

    public TopicsController(TopicsService topicService) {
        this.topicService = topicService;
    }

    @Override
    public ResponseEntity<TopicData> get(Long id) {
        Topic topic = topicService.findById(id);

        LOG.debug("Topic with id={} was found successfully", id);
        return ResponseEntity.ok(new TopicData(topic.getDescription(), topic.getUser().getId()));
    }

    @Override
    public ResponseEntity<Topic> create(TopicData topicData) {
        Topic topic = topicService.create(topicData);

        LOG.debug("Topic with id={} was created successfully", topic.getId());
        return new ResponseEntity<>(topic, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> deleteTopic(Long topicId) {
        topicService.delete(topicId);
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
