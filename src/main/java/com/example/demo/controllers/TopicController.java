package com.example.demo.controllers;

import com.example.demo.models.topic.Topic;
import com.example.demo.models.topic.TopicData;
import com.example.demo.models.topic.TopicId;
import com.example.demo.models.topic.TopicList;
import com.example.demo.models.user.UserId;
import com.example.demo.operations.TopicOperations;
import com.example.demo.services.TopicService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/topics")
@Tag(name = "Topic API", description = "Управление топиками")
public final class TopicController implements TopicOperations {
    private static final Logger LOG = LoggerFactory.getLogger(TopicController.class);

    @Autowired
    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @Override
    public ResponseEntity<Topic> get(Long id) {
        Topic topic = topicService.findById(new TopicId(id));
        LOG.debug("Topic with id={} was found successfully", id);
        return ResponseEntity.ok(topic);
    }

    @Override
    public ResponseEntity<Topic> create(TopicData topicData) {
        Topic topic = topicService.create(new Topic(
                new TopicId(null),
                topicData.description(),
                new UserId(topicData.userId())
                )
        );
        LOG.debug("Topic with id={} was created successfully", topic.id());
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
