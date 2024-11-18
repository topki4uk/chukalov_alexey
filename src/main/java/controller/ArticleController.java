package controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import data.Article;
import data.ArticleID;
import data.CommentID;
import exceptions.ArticleNotFoundException;
import json.AllArticlesResponse;
import json.ArticleCreateRequest;
import json.ArticleDeleteRequest;
import json.ArticleEditRequest;
import json.ArticleGetResponse;
import json.CommentCreateRequest;
import json.CommentDeleteRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.ArticleService;
import spark.Request;
import spark.Response;
import spark.Service;

import java.util.*;

public class ArticleController implements Controller {
  private static final Logger LOG = LoggerFactory.getLogger(ArticleController.class);
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
    deleteComment();
    deleteArticle();
    editArticle();
    getAllArticles();
    createArticle();
    getArticleById();
    addComment();
  }

  private void deleteArticle() {
    service.post(
        "/api/delete/article",
        (Request request, Response response) -> {
          response.type("application/json");
          String body = request.body();
          ArticleDeleteRequest deleteRequest =
              objectMapper.readValue(body, ArticleDeleteRequest.class);

          try {
            articleService.deleteArticle(new ArticleID(deleteRequest.id));
            LOG.debug("Article with id={} was deleted", deleteRequest.id);
            response.status(200);
            return Map.of("statusCode", 200);
          } catch (ArticleNotFoundException e) {
            LOG.warn("Article with id={} didn't exist", deleteRequest.id);
            response.status(404);
            return Map.of("statusCode", 404);
          } catch (Exception exception) {
            LOG.error("Something went wrong!");
            response.status(500);
            return Map.of("statusCode", 500);
          }
        });
  }

  private void deleteComment() {
    service.post(
        "/api/delete/comment",
        (Request request, Response response) -> {
          response.type("application/json");
          String body = request.body();
          CommentDeleteRequest deleteRequest =
              objectMapper.readValue(body, CommentDeleteRequest.class);

          try {
            articleService.deleteComment(
                    new ArticleID(deleteRequest.articleId),
                    new CommentID(deleteRequest.commentId)
            );
            LOG.debug("Comment with id={} was deleted", deleteRequest.commentId);
            response.status(200);
            return Map.of("statusCode", 200);
          } catch (ArticleNotFoundException e) {
            LOG.warn("Comment with id={} didn't exist", deleteRequest.commentId);
            response.status(404);
            return Map.of("statusCode", 404);
          } catch (Exception exception) {
            LOG.error("Something went wrong!");
            response.status(500);
            return Map.of("statusCode", 500);
          }
        });
  }

  private void editArticle() {
    service.post(
        "/api/edit/article",
        (Request request, Response response) -> {
          response.type("application/json");
          String body = request.body();
          ArticleEditRequest editRequest = objectMapper.readValue(body, ArticleEditRequest.class);

          try {
            Article edited = articleService.editArticle(
                    new ArticleID(editRequest.articleId), editRequest.title, editRequest.tags);
            LOG.debug("Article with id={} was edited", editRequest.articleId);
            response.status(200);
            return objectMapper.writeValueAsString(new ArticleGetResponse(
                    edited.getTitle(),
                    edited.getTags(),
                    edited.getComments()
            ));
          } catch (ArticleNotFoundException e) {
            LOG.warn("Article with id={} didn't exist", editRequest.articleId);
            response.status(404);
            return Map.of("statusCode", 404);
          } catch (Exception exception) {
            LOG.error("Something went wrong!");
            response.status(500);
            return Map.of("statusCode", 500);
          }
        });
  }

  private void getAllArticles() {
    service.get(
            "/api/all",
            (Request request, Response response) -> {
              response.type("application/json");

              try {
                List<Article> articles = articleService.getAll();
                LOG.debug("Get all articles {}", articles);
                response.status(200);
                return objectMapper.writeValueAsString(new AllArticlesResponse(articles));
              } catch (Exception e) {
                LOG.error("Something went wrong!");
                response.status(500);
                return Map.of("statusCode", 500);
              }
            });
  }

  private void createArticle() {
    service.post(
            "/api/create/article",
            (Request request, Response response) -> {
              response.type("application/json");
              String body = request.body();
              ArticleCreateRequest createRequest = objectMapper.readValue(
                      body, ArticleCreateRequest.class
              );
              try {
                ArticleID id = articleService.addArticle(createRequest.title, createRequest.tags);
                LOG.debug("Article with id={} was added", id.getID());
                response.status(200);
                return objectMapper.writeValueAsString(id);
              } catch (Exception e) {
                response.status(500);
                LOG.error("Something went wrong!!");
                return Map.of("statusCode", 500);
              }
            }
    );
  }

  private void addComment() {
    service.post(
        "/api/create/comment",
        (Request request, Response response) -> {
          String body = request.body();
          CommentCreateRequest createRequest = objectMapper.readValue(
              body, CommentCreateRequest.class
          );

          try {
            CommentID commentId = articleService.addCommentToArticle(
                    createRequest.articleId,
                    createRequest.text
            );

            LOG.debug("Comment with id={} was added successfully to article with id={}",
                    commentId.getID(),
                    createRequest.articleId.getID()
            );

            response.status(200);
            return commentId;
          } catch (ArticleNotFoundException e) {
            LOG.warn("Comment with id={} was not found!", createRequest.articleId.getID());
            response.status(404);
            return Map.of("statusCode", 404);
          } catch (Exception e) {
            LOG.error("Something went wrong!");
            response.status(500);
            return Map.of("statusCode", 500);
          }
        }
    );
  }

  private void getArticleById() {
    service.get(
        "/api/get",
        (Request request, Response response) -> {
          response.type("application/json");
          ArticleID id = new ArticleID(Long.parseLong(request.queryParams("id")));

          try {
            Article article = articleService.getArticleById(id);

            LOG.debug("Article with id={} was got successfully", id.getID());

            response.status(200);
            return objectMapper.writeValueAsString(new ArticleGetResponse(
                    article.getTitle(),
                    article.getTags(),
                    article.getComments()
            ));
          } catch (ArticleNotFoundException e) {
            LOG.debug("Article with id={} was not found!", id.getID());
            response.status(404);
            return Map.of("responseCode", 404);
          } catch (Exception e) {
            LOG.debug("Something went wrong!!");
            response.status(500);
            return Map.of("responseCode", 500);
          }
        });
  }
}
