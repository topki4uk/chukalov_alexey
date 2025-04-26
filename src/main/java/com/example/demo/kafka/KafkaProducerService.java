package com.example.demo.kafka;

import com.example.demo.model.useraudit.UserAudit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class KafkaProducerService {

  @Autowired
  private final KafkaTemplate<String, String> kafkaTemplate;

  private final ObjectMapper objectMapper;
  private final String topic;

  public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate,
                              ObjectMapper objectMapper,
                              @Value("${topic-name}") String topic) {
    this.kafkaTemplate = kafkaTemplate;
    this.objectMapper = objectMapper;
    this.topic = topic;
  }

  public void sendMessage(UserAudit userAudit) throws JsonProcessingException {
    if (userAudit == null) {
      throw new IllegalArgumentException("userId is null");
    }
    String message = objectMapper.writeValueAsString(userAudit);
    kafkaTemplate.send(topic, message);
    log.debug("Get user ID: {}", message);
  }
}
