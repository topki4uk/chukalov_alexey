package com.example.demo;

import com.example.demo.models.article.exceptions.ArticleInitializationException;
import com.example.demo.models.article.exceptions.ArticleNotFoundException;
import com.example.demo.models.topic.exceptions.TopicAlreadyExistsException;
import com.example.demo.models.topic.exceptions.TopicNotFoundException;
import com.example.demo.models.user.exceptions.EmailConflictException;
import com.example.demo.models.website.exceptions.WebsiteAlreadyExistsException;
import com.example.demo.models.website.exceptions.WebsiteNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DemoExceptionHandler {
    @ExceptionHandler(EmailConflictException.class)
    public ResponseEntity<String> handleEmailConflictException(EmailConflictException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<String> handleArticleNotFoundException(ArticleNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ArticleInitializationException.class)
    public ResponseEntity<String> handleArticleInitializationException(ArticleInitializationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(TopicAlreadyExistsException.class)
    public ResponseEntity<String> handleTopicAlreadyExistsException(TopicAlreadyExistsException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(TopicNotFoundException.class)
    public ResponseEntity<String> handleTopicNotFoundException(TopicNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(WebsiteAlreadyExistsException.class)
    public ResponseEntity<String> handleWebsiteAlreadyExistsException(WebsiteAlreadyExistsException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(WebsiteNotFoundException.class)
    public ResponseEntity<String> handleWebsiteNotFoundException(WebsiteNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
