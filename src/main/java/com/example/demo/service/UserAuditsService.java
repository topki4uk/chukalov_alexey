package com.example.demo.service;

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

  @Autowired
  public UserAuditsService(UserAuditsRepository userAuditsRepository) {
    this.userAuditsRepository = userAuditsRepository;
  }

  public void saveAudit(UserAudit userAudit) {
    userAuditsRepository.save(userAudit);
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

  public void removeAll() {
    userAuditsRepository.deleteAll();
  }
}
