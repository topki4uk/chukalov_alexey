package com.example.demo.operation;

import com.example.demo.model.article.ArticleListData;
import com.example.demo.model.article.ArticleTitleData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/default")
public interface ArticleOperations {
  @GetMapping("/user/{userId}")
  @Operation(summary = "Получение статьи по id")
  @ApiResponse(responseCode = "200", description = "Статья найдена")
  ResponseEntity<ArticleListData> getUserArticles(@Parameter(description = "ID пользователя") @PathVariable Long userId);

  @PatchMapping("/user/{articleId}")
  @Operation(summary = "Обновление названия статьи")
  @ApiResponse(responseCode = "200", description = "Название статьи обновлено")
  ResponseEntity<String> updateArticleTitle(
      @Parameter(description = "ID сайта") @PathVariable Long id,
      @RequestBody ArticleTitleData articleData
  );
}
