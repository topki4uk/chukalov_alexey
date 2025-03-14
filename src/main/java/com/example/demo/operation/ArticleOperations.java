package com.example.demo.operation;

import com.example.demo.model.article.Article;
import com.example.demo.model.article.ArticleData;
import com.example.demo.model.article.ArticleListData;
import com.example.demo.model.article.ArticleTitleData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/default")
public interface ArticleOperations {

  @GetMapping("")
  @Operation(summary = "Получение всех статей")
  @ApiResponse(responseCode = "200", description = "Статьи успешно получены")
  ResponseEntity<ArticleListData> getAllArticles();

  @GetMapping("/{articleId}")
  @Operation(summary = "Получение статьи по ID")
  @ApiResponse(responseCode = "200", description = "Статья успешно получена")
  ResponseEntity<ArticleData> getArticleById(@Parameter(description = "ID статьи") @PathVariable Long articleId);

  @PatchMapping("/{articleId}")
  @Operation(summary = "Обновление названия статьи")
  @ApiResponse(responseCode = "200", description = "Название статьи обновлено")
  ResponseEntity<String> updateArticleTitle(
      @Parameter(description = "ID статьи") @PathVariable Long articleId,
      @RequestBody ArticleTitleData articleData
  );

  @PostMapping("/create")
  @Operation(summary = "Добавление статьи")
  @ApiResponse(responseCode = "201", description = "Статья успешно создана")
  ResponseEntity<Article> createArticle(@RequestBody ArticleData articleData);

  @DeleteMapping("/{articleId}")
  @Operation(summary = "Удаление статьи")
  @ApiResponse(responseCode = "200", description = "Статья успешно удалена")
  ResponseEntity<String> deleteArticle(@Parameter(description = "ID статьи") @PathVariable Long articleId);
}
