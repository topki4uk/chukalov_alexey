package com.example.demo.outbox;

import com.example.demo.DbSuite;
import com.example.demo.controller.ArticlesController;
import com.example.demo.model.article.ArticleData;
import com.example.demo.model.useraudit.UserAudit;
import com.example.demo.model.useraudit.UserAuditOutbox;
import com.example.demo.service.OutboxService;
import com.example.demo.service.UserAuditsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class OutboxTest extends DbSuite {
  @Container
  @ServiceConnection
  public static final KafkaContainer KAFKA =
      new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.4.0"));

  private final static UUID USER_ID = UUID.fromString("b9d34b4a-2634-4e86-a197-d30c8b18d435");

  @Autowired
  private ArticlesController articlesController;

  @Autowired
  private UserAuditsService userAuditsService;

  @Autowired
  private OutboxService outboxService;

  @BeforeEach
  public void cleanUp() {
    outboxService.removeAll();
    userAuditsService.removeAll();
  }

  @Test
  public void shouldCreateOutboxTopic() {
    UserAudit userAudit = new UserAudit();
    userAudit.setUserId(UUID.randomUUID());
    userAudit.setEventDetails("test");
    userAudit.setEventType("test");
    userAudit.setEventTime(new Timestamp(System.currentTimeMillis()));

    assertDoesNotThrow(() -> outboxService.save(userAudit.toOutbox()));

    List<UserAuditOutbox> res = outboxService.removeAll();
    assertEquals(1, res.size());
    assertEquals(userAudit, res.get(0).toUserAudit());
  }

  @Test
  public void shouldSendToMainTable() throws InterruptedException {
    articlesController.createArticle(
        Map.of("user_id", USER_ID.toString()),
        new ArticleData("title", "url", 1L, 1L)
        );

    Thread.sleep(10 * 1000);

    List<UserAuditOutbox> res = outboxService.removeAll();
    assertEquals(0, res.size());

    List<UserAudit> userAudits = userAuditsService.getUserAudits(USER_ID);
    assertEquals(1, userAudits.size());
    assertEquals(USER_ID, userAudits.get(0).getUserId());
  }

  @Test
  public void shouldNotSendToMainTable() {
    articlesController.createArticle(
        Map.of("user_id", USER_ID.toString()),
        new ArticleData("title", "url", 1L, 1L)
    );

    List<UserAuditOutbox> res = outboxService.removeAll();
    assertEquals(1, res.size());

    List<UserAudit> userAudits = userAuditsService.getUserAudits(USER_ID);
    assertEquals(0, userAudits.size());
  }
}
