package com.example.demo.service;

import com.example.demo.kafka.KafkaConsumerService;
import com.example.demo.model.useraudit.UserAudit;
import com.example.demo.repository.UserAuditsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class UserAuditsService {

  private final UserAuditsRepository userAuditsRepository;
  private final KafkaConsumerService kafkaConsumerService;

  @Autowired
  public UserAuditsService(UserAuditsRepository userAuditsRepository, KafkaConsumerService kafkaConsumerService) {
    this.userAuditsRepository = userAuditsRepository;
    this.kafkaConsumerService = kafkaConsumerService;
  }

  public void saveUserAudits() {
    userAuditsRepository.save(
        kafkaConsumerService.receiveMessage(UserAuditsService.class.getSimpleName())
    );
  }

  public UserAudit saveAudit(UUID userId, String eventType, String eventDetails) {
    UserAudit userAudit = new UserAudit();
    userAudit.setUserId(userId);
    userAudit.setEventTime(new Timestamp(System.currentTimeMillis()));
    userAudit.setEventType(eventType);
    userAudit.setEventDetails(eventDetails);
    userAuditsRepository.save(userAudit);
    return userAudit;
  }

  public List<UserAudit> getUserAudits(UUID userId) {
    return userAuditsRepository.getByUserId(userId);
  }
}
