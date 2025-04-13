package com.example.homework.controller;

import com.example.homework.model.topic.Topic;
import com.example.homework.model.topic.TopicData;
import com.example.homework.model.topic.TopicList;
import com.example.homework.operation.TopicOperations;
import com.example.homework.service.TopicsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/topics")
@AllArgsConstructor
@Tag(name = "Topic API", description = "Управление топиками")
public class TopicsController implements TopicOperations {

  private final TopicsService topicService;

  @Override
  public ResponseEntity<TopicData> get(Long id) {
    Topic topic = topicService.findById(id);

    log.debug("Topic with id={} was found successfully", id);
    return ResponseEntity.ok(new TopicData(topic.getDescription(), topic.getUser().getId()));
  }

  @Override
  public ResponseEntity<Topic> create(TopicData topicData) {
    Topic topic = topicService.create(topicData);

    log.debug("Topic with id={} was created successfully", topic.getId());
    return new ResponseEntity<>(topic, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<String> deleteTopic(Long topicId) {
    topicService.delete(topicId);

    log.debug("Topic with id={} was deleted successfully", topicId);
    return ResponseEntity.ok("Topic deleted!");
  }

  @Override
  public ResponseEntity<TopicList> getUserTopics(Long userId) {
    List<Topic> topics = topicService.getUserTopics(userId);

    log.debug("Topics for user with id={} were got successfully", userId);
    return ResponseEntity.ok(new TopicList(topics));
  }
}
