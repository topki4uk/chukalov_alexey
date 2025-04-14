package com.example.demo.kafka;

import com.example.demo.model.useraudit.UserAudit;
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

  @KafkaListener(topics = {"${topic-to-consume-message}"})
  public void receiveMessage(String message) {
    log.debug("Receive message from Kafka topic: {}", message);
  }
}
