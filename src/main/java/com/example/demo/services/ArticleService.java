package com.example.demo.services;

import com.example.demo.models.article.Article;
import com.example.demo.models.article.ArticleId;
import com.example.demo.models.article.repositories.ArticleRepository;
import com.example.demo.models.article.repositories.InMemoryArticleRepository;
import com.example.demo.models.user.UserId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public final class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(InMemoryArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Optional<Article> findById(ArticleId articleId) {
        return articleRepository.findById(articleId);
    }

    public List<Article> getAllUnknown(UserId userId) {
        return articleRepository.getAllUnknown(userId);
    }
}
