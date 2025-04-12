package com.example.homework.service;

import com.example.homework.model.user.User;
import com.example.homework.model.useraudit.UserAudit;
import com.example.homework.repository.UserAuditsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserAuditsService {

  private final UserAuditsRepository userAuditsRepository;

  @Autowired
  public UserAuditsService(UserAuditsRepository userAuditsRepository) {
    this.userAuditsRepository = userAuditsRepository;
  }

  public void saveAudit(User user, String eventType, String eventDetails) {
    UserAudit userAudit = new UserAudit();
    userAudit.setUserId(user.getId());
    userAudit.setEventTime(new Timestamp(System.currentTimeMillis()));
    userAudit.setEventType(eventType);
    userAudit.setEventDetails(eventDetails);
    userAuditsRepository.save(userAudit);
  }

  public List<UserAudit> getUserAudits(User user) {
    return userAuditsRepository.getByUserId(user.getId());
  }
}
