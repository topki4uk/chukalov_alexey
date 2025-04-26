package com.example.demo.kafka;

import com.example.demo.model.useraudit.UserAudit;
import com.example.demo.service.UserAuditsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaConsumerService {

  private ObjectMapper objectMapper;
  private UserAuditsService userAuditsService;

  @KafkaListener(topics = "${topic-name}", groupId = "${group.id}")
  public void receiveMessage(String message) throws JsonProcessingException {
    UserAudit userAudit = objectMapper.readValue(message, UserAudit.class);
    log.debug("Receive message from Kafka topic: {}", message);
    userAuditsService.saveAudit(userAudit);
  }
}
