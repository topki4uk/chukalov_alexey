package com.example.demo;

import com.example.demo.controllers.ArticleController;
import com.example.demo.models.article.exceptions.ArticleInitializationException;
import com.example.demo.models.article.exceptions.ArticleNotFoundException;
import com.example.demo.models.topic.exceptions.TopicAlreadyExistsException;
import com.example.demo.models.topic.exceptions.TopicNotFoundException;
import com.example.demo.models.user.exceptions.EmailConflictException;
import com.example.demo.models.website.exceptions.WebsiteAlreadyExistsException;
import com.example.demo.models.website.exceptions.WebsiteNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<String> handleArticleNotFoundException(ArticleNotFoundException e) {
        LOG.warn("Article not found: {}", e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ArticleInitializationException.class)
    public ResponseEntity<String> handleArticleInitializationException(ArticleInitializationException e) {
        LOG.warn("Article not initialized: {}", e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(TopicAlreadyExistsException.class)
    public ResponseEntity<String> handleTopicAlreadyExistsException(TopicAlreadyExistsException e) {
        LOG.warn("Topic already exists: {}", e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(TopicNotFoundException.class)
    public ResponseEntity<String> handleTopicNotFoundException(TopicNotFoundException e) {
        LOG.warn("Topic not found: {}", e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(WebsiteAlreadyExistsException.class)
    public ResponseEntity<String> handleWebsiteAlreadyExistsException(WebsiteAlreadyExistsException e) {
        LOG.warn("Website already exists: {}", e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(WebsiteNotFoundException.class)
    public ResponseEntity<String> handleWebsiteNotFoundException(WebsiteNotFoundException e) {
        LOG.warn("Website not found: {}", e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
