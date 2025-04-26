package com.example.demo.controller;

import com.example.demo.kafka.KafkaProducerService;
import com.example.demo.model.article.ArticleListData;
import com.example.demo.model.article.Article;
import com.example.demo.model.article.ArticleData;
import com.example.demo.model.article.ArticleTitleData;
import com.example.demo.model.useraudit.UserAudit;
import com.example.demo.model.useraudit.UserAuditOutbox;
import com.example.demo.operation.ArticleOperations;
import com.example.demo.service.ArticlesService;
import com.example.demo.service.OutboxService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@EnableScheduling
@RequestMapping("api/articles")
@Tag(name = "Article API", description = "Управление статьями")
public class ArticlesController implements ArticleOperations {

  private final ArticlesService articleService;
  private final KafkaProducerService kafkaProducerService;
  private final OutboxService outbox;

  public ArticlesController(ArticlesService articleService, KafkaProducerService kafkaProducerService, OutboxService outbox) {
    this.articleService = articleService;
    this.kafkaProducerService = kafkaProducerService;
    this.outbox = outbox;
  }

  @Override
  public ResponseEntity<ArticleListData> getAllArticles(Map<String, String> headers) {
    if (!headers.containsKey("user_id")) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    UUID userId = UUID.fromString(headers.get("user_id"));
    UserAudit userAudit = new UserAudit(userId, new Timestamp(System.currentTimeMillis()), "get", "get all articles");
    outbox.save(userAudit.toOutbox());
    ArticleListData articles = articleService.findAll();

    log.debug("Articles count: {}", articles.articleDataList().size());
    return new ResponseEntity<>(articles, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<ArticleData> getArticleById(Map<String, String> headers, Long articleId) {
    if (!headers.containsKey("user_id")) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    UUID userId = UUID.fromString(headers.get("user_id"));
    Article article = articleService.findById(articleId);
    ArticleData articleData = new ArticleData(
        article.getTitle(),
        article.getUrl(),
        article.getTopicId(),
        article.getWebsiteId()
    );
    UserAudit userAudit = new UserAudit(userId, new Timestamp(System.currentTimeMillis()), "get", "get all articles");
    outbox.save(userAudit.toOutbox());

    log.debug("Article data: {}", articleData);
    return new ResponseEntity<>(articleData, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<String> updateArticleTitle(Map<String, String> headers, Long articleId, ArticleTitleData newTitle) {
    if (!headers.containsKey("user_id")) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    UUID userId = UUID.fromString(headers.get("user_id"));
    articleService.updateTitle(articleId, newTitle.title());
    UserAudit userAudit = new UserAudit(userId, new Timestamp(System.currentTimeMillis()), "get", "get all articles");
    outbox.save(userAudit.toOutbox());

    log.debug("Article`s title with id={} was updated", articleId);
    return ResponseEntity.ok("Title updated");
  }

  @Override
  public ResponseEntity<Article> createArticle(Map<String, String> headers, ArticleData articleData) {
    if (!headers.containsKey("user_id")) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    UUID userId = UUID.fromString(headers.get("user_id"));
    Article article = articleService.create(articleData);
    UserAudit userAudit = new UserAudit(userId, new Timestamp(System.currentTimeMillis()), "get", "get all articles");
    outbox.save(userAudit.toOutbox());

    log.debug("Article`s title with id={} was created", article.getId());
    return new ResponseEntity<>(article, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<String> deleteArticle(Map<String, String> headers, Long articleId) {
    if (!headers.containsKey("user_id")) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    UUID userId = UUID.fromString(headers.get("user_id"));
    articleService.delete(articleId);
    UserAudit userAudit = new UserAudit(userId, new Timestamp(System.currentTimeMillis()), "get", "get all articles");
    outbox.save(userAudit.toOutbox());

    log.debug("Article`s id with id={} was deleted", articleId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Scheduled(fixedRate = 5 * 1000)
  public void sendToKafka() throws JsonProcessingException {
    List<UserAuditOutbox> userAuditOutboxList = outbox.removeAll();
    for (UserAuditOutbox userAuditOutbox : userAuditOutboxList) {
      kafkaProducerService.sendMessage(userAuditOutbox.toUserAudit());
    }
    log.debug("All outbox was cleaned!");
  }
}
