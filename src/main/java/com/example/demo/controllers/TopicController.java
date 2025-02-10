package com.example.demo.controllers;

import com.example.demo.models.topic.Topic;
import com.example.demo.models.topic.TopicData;
import com.example.demo.models.topic.TopicId;
import com.example.demo.models.user.UserId;
import com.example.demo.services.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/topics")
public final class TopicController {
    private static final Logger LOG = LoggerFactory.getLogger(TopicController.class);
    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> get(@PathVariable Long id) {
        Topic topic = topicService.findById(new TopicId(id)).get();
        LOG.debug("Topic with id={} was found successfully", id);
        return ResponseEntity.ok(topic);
    }

    @PostMapping("/create")
    public ResponseEntity<Topic> create(@RequestBody TopicData topicData) {
        Topic topic = topicService.create(new Topic(
                new TopicId(null),
                topicData.description(),
                new UserId(topicData.userId())
                )
        );
        LOG.debug("Topic with id={} was created successfully", topic.id());
        return ResponseEntity.ok(topic);
    }

    @DeleteMapping("/{userId}/{topicId}")
    public ResponseEntity<String> deleteTopic(@PathVariable Long userId, @PathVariable Long topicId) {
        topicService.delete(new UserId(userId), new TopicId(topicId));
        LOG.debug("Topic with id={} was deleted successfully", topicId);
        return ResponseEntity.ok("Topic deleted!");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Topic>> getUserTopics(@PathVariable Long userId) {
        List<Topic> topics = topicService.getAllTopics();
        LOG.debug("Topics for user with id={} were got successfully", userId);
        return ResponseEntity.ok(topics);
    }
}
