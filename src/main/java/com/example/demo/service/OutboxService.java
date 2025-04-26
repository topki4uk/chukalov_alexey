package com.example.demo.service;

import com.example.demo.model.useraudit.UserAuditOutbox;
import com.example.demo.repository.OutboxUserAuditRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OutboxService {
  private final OutboxUserAuditRepository outboxRepository;

  public OutboxService(OutboxUserAuditRepository outboxRepository) {
    this.outboxRepository = outboxRepository;
  }

  @Transactional()
  public void save(UserAuditOutbox userAuditOutbox) {
    outboxRepository.save(userAuditOutbox);
  }

  @Transactional()
  public List<UserAuditOutbox> removeAll() {
    List<UserAuditOutbox> userAuditOutboxList = outboxRepository.findAll();
    outboxRepository.deleteAll();
    return userAuditOutboxList;
  }
}
