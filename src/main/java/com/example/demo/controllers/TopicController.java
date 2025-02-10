package com.example.demo.controllers;

import com.example.demo.models.topic.Topic;
import com.example.demo.models.topic.TopicData;
import com.example.demo.models.topic.TopicId;
import com.example.demo.models.user.UserId;
import com.example.demo.services.TopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/topics")
public final class TopicController {
    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> get(@PathVariable Long id) {
        Topic topic = topicService.findById(new TopicId(id)).get();
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
        return ResponseEntity.ok(topic);
    }

    @DeleteMapping("/{userId}/{topicId}")
    public ResponseEntity<String> deleteTopic(@PathVariable Long userId, @PathVariable Long topicId) {
        topicService.delete(new UserId(userId), new TopicId(topicId));
        return ResponseEntity.ok("Topic deleted!");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Topic>> getUserTopics(@PathVariable Long userId) {
        List<Topic> topics = topicService.getAllTopics();
        return ResponseEntity.ok(topics);
    }
}
