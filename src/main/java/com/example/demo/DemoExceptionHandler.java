package com.example.demo;

import com.example.demo.models.article.exceptions.ArticleInitializationException;
import com.example.demo.models.article.exceptions.ArticleNotFoundException;
import com.example.demo.models.topic.exceptions.TopicAlreadyExistsException;
import com.example.demo.models.topic.exceptions.TopicNotFoundException;
import com.example.demo.models.user.exceptions.EmailConflictException;
import com.example.demo.models.user.exceptions.UserInitializationException;
import com.example.demo.models.user.exceptions.UserNotFoundException;
import com.example.demo.models.website.exceptions.WebsiteAlreadyExistsException;
import com.example.demo.models.website.exceptions.WebsiteNotFoundException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DemoExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(DemoExceptionHandler.class);

    @ExceptionHandler(EmailConflictException.class)
    public ResponseEntity<String> handleEmailConflictException(EmailConflictException e) {
        LOG.warn("Email conflict: {}", e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ApiResponse(
        responseCode = "404",
        description = "Статья не найдена",
        content = @Content(schema = @Schema(implementation = ArticleNotFoundException.class))
    )
    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<String> handleArticleNotFoundException(ArticleNotFoundException e) {
        LOG.warn("Article not found: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ApiResponse(
        responseCode = "409",
        description = "Статья не инициализована",
        content = @Content(schema = @Schema(implementation = ArticleInitializationException.class))
    )
    @ExceptionHandler(ArticleInitializationException.class)
    public ResponseEntity<String> handleArticleInitializationException(ArticleInitializationException e) {
        LOG.warn("Article not initialized: {}", e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ApiResponse(
        responseCode = "409",
        description = "Топик уже существует",
        content = @Content(schema = @Schema(implementation = TopicAlreadyExistsException.class))
    )
    @ExceptionHandler(TopicAlreadyExistsException.class)
    public ResponseEntity<String> handleTopicAlreadyExistsException(TopicAlreadyExistsException e) {
        LOG.warn("Topic already exists: {}", e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ApiResponse(
        responseCode = "404",
        description = "Топик не инициализован",
        content = @Content(schema = @Schema(implementation = TopicNotFoundException.class))
    )
    @ExceptionHandler(TopicNotFoundException.class)
    public ResponseEntity<String> handleTopicNotFoundException(TopicNotFoundException e) {
        LOG.warn("Topic not found: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ApiResponse(
        responseCode = "409",
        description = "Сайт уже существует",
        content = @Content(schema = @Schema(implementation = WebsiteAlreadyExistsException.class))
    )
    @ExceptionHandler(WebsiteAlreadyExistsException.class)
    public ResponseEntity<String> handleWebsiteAlreadyExistsException(WebsiteAlreadyExistsException e) {
        LOG.warn("Website already exists: {}", e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ApiResponse(
        responseCode = "404",
        description = "Сайт не найден",
        content = @Content(schema = @Schema(implementation = WebsiteNotFoundException.class))
    )
    @ExceptionHandler(WebsiteNotFoundException.class)
    public ResponseEntity<String> handleWebsiteNotFoundException(WebsiteNotFoundException e) {
        LOG.warn("Website not found: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ApiResponse(
        responseCode = "409",
        description = "Пользователь не инициализирован",
        content = @Content(schema = @Schema(implementation = UserInitializationException.class))
    )
    @ExceptionHandler(UserInitializationException.class)
    public ResponseEntity<String> handleUserInitializationException(UserInitializationException e) {
        LOG.warn("Website not found: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ApiResponse(
        responseCode = "404",
        description = "Пользователь не найден",
        content = @Content(schema = @Schema(implementation = UserNotFoundException.class))
    )
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        LOG.warn("Website not found: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
