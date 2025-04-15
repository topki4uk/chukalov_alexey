package com.example.demo.kafka;

import com.example.demo.model.useraudit.UserAudit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class KafkaProducerService {

  private final KafkaTemplate<String, String> kafkaTemplate;
  private final ObjectMapper objectMapper;
  private final String topic;

  public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate,
                              ObjectMapper objectMapper,
                              @Value("${topic-to-send-message}") String topic) {
    this.kafkaTemplate = kafkaTemplate;
    this.objectMapper = objectMapper;
    this.topic = topic;
  }

  public void sendMessage(UserAudit userId) throws JsonProcessingException {
    if (userId == null) {
      throw new IllegalArgumentException("userId is null");
    }
    String message = objectMapper.writeValueAsString(userId);
    CompletableFuture<SendResult<String, String>> sendResult = kafkaTemplate.send(topic, message);
    log.debug("Get user ID: {}", message);
  }
}
