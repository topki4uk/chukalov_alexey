package com.example.demo.controllers;


import com.example.demo.models.article.Article;
import com.example.demo.models.article.ArticleData;
import com.example.demo.models.user.UserId;
import com.example.demo.services.ArticleService;
import com.example.demo.services.TopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/articles")
public final class ArticleController {
    private final ArticleService articleService;
    private final TopicService topicService;

    public ArticleController(ArticleService articleService, TopicService topicService) {
        this.articleService = articleService;
        this.topicService = topicService;
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<ArticleData>> getUserArticles(@PathVariable Long userId) {
        List<Article> articles = articleService.getAllUnknown(new UserId(userId));
        List<ArticleData> articleData = new ArrayList<>();

        for (Article article : articles) {
            String topicDescription = topicService.findById(article.topicId()).get().description();
            articleData.add(new ArticleData(article.title(), article.url(), article.createdAt(), topicDescription));
        }
        return ResponseEntity.ok(articleData);
    }
}
