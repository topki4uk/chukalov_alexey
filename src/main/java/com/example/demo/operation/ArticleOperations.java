package com.example.demo.operation;

import com.example.demo.model.article.Article;
import com.example.demo.model.article.ArticleData;
import com.example.demo.model.article.ArticleListData;
import com.example.demo.model.article.ArticleTitleData;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RequestMapping("/default")
public interface ArticleOperations {

  @GetMapping("")
  @Operation(summary = "Получение всех статей")
  @ApiResponse(responseCode = "200", description = "Статьи успешно получены")
  ResponseEntity<ArticleListData> getAllArticles(@RequestHeader Map<String, String> headers) throws JsonProcessingException;

  @GetMapping("/{articleId}")
  @Operation(summary = "Получение статьи по ID")
  @ApiResponse(responseCode = "200", description = "Статья успешно получена")
  ResponseEntity<ArticleData> getArticleById(
      @RequestHeader Map<String, String> headers,
      @Parameter(description = "ID статьи") @PathVariable Long articleId) throws JsonProcessingException;

  @PatchMapping("/{articleId}")
  @Operation(summary = "Обновление названия статьи")
  @ApiResponse(responseCode = "200", description = "Название статьи обновлено")
  ResponseEntity<String> updateArticleTitle(
      @RequestHeader Map<String, String> headers,
      @Parameter(description = "ID статьи") @PathVariable Long articleId,
      @RequestBody ArticleTitleData articleData
  ) throws JsonProcessingException;

  @PostMapping("/create")
  @Operation(summary = "Добавление статьи")
  @ApiResponse(responseCode = "201", description = "Статья успешно создана")
  ResponseEntity<Article> createArticle(
      @RequestHeader Map<String, String> headers,
      @RequestBody ArticleData articleData) throws JsonProcessingException;

  @DeleteMapping("/{articleId}")
  @Operation(summary = "Удаление статьи")
  @ApiResponse(responseCode = "200", description = "Статья успешно удалена")
  ResponseEntity<String> deleteArticle(
      @RequestHeader Map<String, String> headers,
      @Parameter(description = "ID статьи") @PathVariable Long articleId) throws JsonProcessingException;
}
