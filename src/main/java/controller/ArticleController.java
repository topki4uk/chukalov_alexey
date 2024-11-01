package controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import data.Article;
import data.ArticleID;
import data.Comment;
import data.CommentID;
import json.ArticleCreateRequest;
import json.ArticleCreateResponse;
import json.ErrorResponse;
import org.slf4j.LoggerFactory;
import services.ArticleService;
import spark.Request;
import spark.Response;
import spark.Service;

import java.util.*;
import java.util.logging.Logger;

public class ArticleController implements Controller {
  private final Service service;
  private final ArticleService articleService;
  private final ObjectMapper objectMapper;

  public ArticleController(Service service, ObjectMapper objectMapper, ArticleService articleService) {
    this.objectMapper = objectMapper;
    this.articleService = articleService;
    this.service = service;
  }

  @Override
  public void initEndpoints() {
    getArticle();
    createArticle();
  }

  private void getArticle() {
    service.get(
            "/api/get/",
            (Request request, Response response) -> {
              response.type("application/json");
              return objectMapper.writeValueAsString(new HashMap<>(Map.of("key", "value")));
            });
  }

  private void createArticle() {
    service.post(
            "/api/create",
            (Request request, Response response) -> {
              response.type("application/json");
              String body = request.body();
              ArticleCreateRequest createRequest = objectMapper.readValue(
                      body, ArticleCreateRequest.class
              );
              try {
                ArticleID id = articleService.addArticle(createRequest.title, createRequest.tags, createRequest.comments);
                response.status(201);
                return objectMapper.writeValueAsString(new ArticleCreateResponse(id));
              } catch (Exception e) {
                response.status(400);
                return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
              }
            }
    );
  }
}
