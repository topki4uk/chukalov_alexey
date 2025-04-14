package com.example.homework.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.homework.model.useraudit.UserAudit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.cassandra.CassandraInvalidQueryException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.cassandra.contact-points=127.0.0.1",
    "spring.cassandra.port=9042",
    "spring.cassandra.keyspace-name=hw6",
    "spring.cassandra.local-datacenter=datacenter1"
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserAuditTest {

  @Autowired
  private UserAuditsService userAuditService;

  private final static UUID USER_1 = UUID.randomUUID();

  @Test
  public void createUserAudit() {
    userAuditService.saveAudit(USER_1, "event1", "details1");
    List<UserAudit> userAuditList = userAuditService.getUserAudits(USER_1);

    assertEquals(1, userAuditList.size());
    assertEquals(USER_1, userAuditList.get(0).getUserId());
    assertEquals("event1", userAuditList.get(0).getEventType());
    assertEquals("details1", userAuditList.get(0).getEventDetails());
  }


  @Test
  public void createWrongUserAudit() {
    assertThrows(CassandraInvalidQueryException.class, () ->
        userAuditService.saveAudit(null, "event1", "details1")
    );
  }

  @Test
  public void getWrongUserAudits() {
    assertThrows(CassandraInvalidQueryException.class, () -> userAuditService.getUserAudits(null));
  }
}
