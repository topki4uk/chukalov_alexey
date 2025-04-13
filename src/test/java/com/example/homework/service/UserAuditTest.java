package com.example.homework.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.homework.exception.UserNotFoundException;
import com.example.homework.model.user.User;
import com.example.homework.model.user.UserData;
import com.example.homework.model.user.UserId;
import com.example.homework.model.useraudit.UserAudit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    List<UserAudit> userAuditList = userAuditService.getUserAudits(USER_1);

    assertEquals(2, userAuditList.size());
    assertEquals(USER_1.getId(), userAuditList.get(0).getUserId());
    assertEquals("event1", userAuditList.get(0).getEventType());
    assertEquals("details1", userAuditList.get(0).getEventDetails());
  }

  @Test
  public void createUser() {
    User tempUser = usersService.register(new UserData("usertemp", "usertemp@email.com", "passwordtemp"));
    List<UserAudit> userAuditList = userAuditService.getUserAudits(tempUser);

    assertEquals(1, userAuditList.size());
    assertEquals("register",  userAuditList.get(0).getEventType());
    assertEquals("User registered successfully", userAuditList.get(0).getEventDetails());

    usersService.delete(tempUser.getId());
  }

  @Test
  public void findUser() {
    usersService.findById(new UserId(USER_1.getId()));
    List<UserAudit> userAuditList = userAuditService.getUserAudits(USER_1);
    assertEquals(2, userAuditList.size());
    assertEquals("find", userAuditList.get(0).getEventType());
    assertEquals("User found successfully", userAuditList.get(0).getEventDetails());
  }

  @Test
  public void notFindUser() {
    UUID id = UUID.randomUUID();
    assertThrows(UserNotFoundException.class, () -> usersService.findById(new UserId(id)));
    List<UserAudit> userAuditList = userAuditService.getUserAudits(new User(id, null, null, null));
    assertEquals(1, userAuditList.size());
    assertEquals("not found", userAuditList.get(0).getEventType());
    assertEquals("user not found", userAuditList.get(0).getEventDetails());
  }

  @Test
  public void updateUser() {
    usersService.update(new UserData(USER_1.getEmail(), USER_1.getPassword(), USER_1.getUsername()), USER_1.getId());
    List<UserAudit> userAuditList = userAuditService.getUserAudits(USER_1);
    assertEquals(2, userAuditList.size());
    assertEquals("update", userAuditList.get(0).getEventType());
    assertEquals("User updated successfully", userAuditList.get(0).getEventDetails());
  }

  @Test
  public void notUpdateUser() {
    UUID id = UUID.randomUUID();
    assertThrows(UserNotFoundException.class, () -> usersService.update(new UserData("test", "test", "test"), id));
    List<UserAudit> userAuditList = userAuditService.getUserAudits(new User(id, null, null, null));
    assertEquals(1, userAuditList.size());
    assertEquals("not update", userAuditList.get(0).getEventType());
    assertEquals("user not found", userAuditList.get(0).getEventDetails());
  }

  @Test
  public void deleteUser() {
    User tempUser = usersService.register(new UserData("usertemp", "usertemp@email.com", "passwordtemp"));
    usersService.delete(tempUser.getId());
    List<UserAudit> userAuditList = userAuditService.getUserAudits(tempUser);

    assertEquals(2, userAuditList.size());
    assertEquals("delete",  userAuditList.get(0).getEventType());
    assertEquals("User deleted successfully", userAuditList.get(0).getEventDetails());
  }

  @Test
  public void notDeleteUser() {
    UUID id = UUID.randomUUID();
    assertThrows(UserNotFoundException.class, () -> usersService.delete(id));
    List<UserAudit> userAuditList = userAuditService.getUserAudits(new User(id, null, null, null));
    assertEquals(1, userAuditList.size());
    assertEquals("not delete", userAuditList.get(0).getEventType());
    assertEquals("user not found", userAuditList.get(0).getEventDetails());
  }
}
