package com.example.demo.controller;

import com.example.demo.kafka.KafkaProducerService;
import com.example.demo.model.article.ArticleListData;
import com.example.demo.model.article.Article;
import com.example.demo.model.article.ArticleData;
import com.example.demo.model.article.ArticleTitleData;
import com.example.demo.model.useraudit.UserAudit;
import com.example.demo.operation.ArticleOperations;
import com.example.demo.service.ArticlesService;
import com.example.demo.service.UserAuditsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/articles")
@Tag(name = "Article API", description = "Управление статьями")
public class ArticlesController implements ArticleOperations {

  private final ArticlesService articleService;
  private final UserAuditsService userAuditsService;
  private final KafkaProducerService kafkaProducerService;

  public ArticlesController(ArticlesService articleService, UserAuditsService userAuditsService, KafkaProducerService kafkaProducerService) {
    this.articleService = articleService;
    this.userAuditsService = userAuditsService;
    this.kafkaProducerService = kafkaProducerService;
  }

  @Override
  public ResponseEntity<ArticleListData> getAllArticles(Map<String, String> headers) throws JsonProcessingException {
    if (!headers.containsKey("user_id")) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    UUID userId = UUID.fromString(headers.get("user_id"));
    UserAudit userAudit = userAuditsService.saveAudit(userId, "get", "get all articles");
    kafkaProducerService.sendMessage(userAudit);
    ArticleListData articles = articleService.findAll();

    log.debug("Articles count: {}", articles.articleDataList().size());
    return new ResponseEntity<>(articles, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<ArticleData> getArticleById(Map<String, String> headers, Long articleId) throws JsonProcessingException {
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
    UserAudit userAudit = userAuditsService.saveAudit(userId, "get", "get article by id");
    kafkaProducerService.sendMessage(userAudit);

    log.debug("Article data: {}", articleData);
    return new ResponseEntity<>(articleData, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<String> updateArticleTitle(Map<String, String> headers, Long articleId, ArticleTitleData newTitle) throws JsonProcessingException {
    if (!headers.containsKey("user_id")) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    UUID userId = UUID.fromString(headers.get("user_id"));
    articleService.updateTitle(articleId, newTitle.title());
    UserAudit userAudit = userAuditsService.saveAudit(userId, "update", "update article title");
    kafkaProducerService.sendMessage(userAudit);

    log.debug("Article`s title with id={} was updated", articleId);
    return ResponseEntity.ok("Title updated");
  }

  @Override
  public ResponseEntity<Article> createArticle(Map<String, String> headers, ArticleData articleData) throws JsonProcessingException {
    if (!headers.containsKey("user_id")) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    UUID userId = UUID.fromString(headers.get("user_id"));
    Article article = articleService.create(articleData);
    UserAudit userAudit = userAuditsService.saveAudit(userId, "create", "create article");
    kafkaProducerService.sendMessage(userAudit);

    log.debug("Article`s title with id={} was created", article.getId());
    return new ResponseEntity<>(article, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<String> deleteArticle(Map<String, String> headers, Long articleId) throws JsonProcessingException {
    if (!headers.containsKey("user_id")) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    UUID userId = UUID.fromString(headers.get("user_id"));
    articleService.delete(articleId);
    UserAudit userAudit = userAuditsService.saveAudit(userId, "delete", "delete article by id");
    kafkaProducerService.sendMessage(userAudit);

    log.debug("Article`s id with id={} was deleted", articleId);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
