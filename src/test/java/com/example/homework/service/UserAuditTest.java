package com.example.homework.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.homework.model.user.User;
import com.example.homework.model.user.UserData;
import com.example.homework.model.useraudit.UserAudit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
public class UserAuditTest {

  @Autowired
  private UserAuditsService userAuditService;

  @Autowired
  private UsersService usersService;

  private static User USER_1;
  private static User USER_2;

  @BeforeEach
  public void setUp() {
    USER_1 = usersService.register(new UserData("user1", "user1@email.com", "password1"));
    USER_2 = usersService.register(new UserData("user2", "user2@email.com", "password2"));
  }

  @AfterEach
  public void tearDown() {
    usersService.delete(USER_1.getId());
    usersService.delete(USER_2.getId());
  }

  @Test
  public void createUserAudit() {
    userAuditService.saveAudit(USER_1, "event1", "details1");
    userAuditService.saveAudit(USER_2, "event2", "details2");
    List<UserAudit> userAuditList = userAuditService.getUserAudits(USER_1);

    assertEquals(1, userAuditList.size());
    assertEquals(USER_1.getId(), userAuditList.get(0).getUserId());
    assertEquals("event1", userAuditList.get(0).getEventType());
    assertEquals("details1", userAuditList.get(0).getEventDetails());
  }
}
