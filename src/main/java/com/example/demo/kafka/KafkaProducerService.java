package com.example.demo.kafka;

import com.example.demo.model.useraudit.UserAudit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducerService {

  private final KafkaTemplate<String, String> kafkaTemplate;
  private final ObjectMapper objectMapper;
  private final String topic;

  public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate,
                              ObjectMapper objectMapper,
                              @Value("${topic-to-consume-message}") String topic) {
    this.kafkaTemplate = kafkaTemplate;
    this.objectMapper = objectMapper;
    this.topic = topic;
  }

  public void sendMessage(UserAudit userAudit) throws JsonProcessingException {
    String message = objectMapper.writeValueAsString(userAudit);
    kafkaTemplate.send(topic, message);
    log.debug(message);
  }
}
