package com.example.demo.service;

import com.example.demo.model.article.Article;
import com.example.demo.model.article.ArticleId;
import com.example.demo.exception.ArticleNotFoundException;
import com.example.demo.repository.ArticlesRepository;
import com.example.demo.model.user.UserId;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticlesService {
    private final ArticlesRepository articleRepository;

    @Cacheable(value = "myCache", key = "#articleId")
    public Article findById(ArticleId articleId) {
        return articleRepository
            .findById(articleId)
            .orElseThrow(() -> new ArticleNotFoundException(articleId));
    }

    @CachePut(value = "myCache", key = "#article")
    public void updateTitle(Article article) {
        articleRepository.updateTitle(article);
    }

    @Cacheable(value = "myCache", key = "#userId")
    public List<Article> getAllUnknown(UserId userId) {
        return articleRepository.getAllUnknown(userId);
    }
}
