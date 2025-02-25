package com.example.demo.repository;

import com.example.demo.model.article.Article;
import com.example.demo.model.article.ArticleId;
import com.example.demo.model.user.UserId;

import java.util.List;
import java.util.Optional;

public interface ArticlesRepository {
    Optional<Article> findById(ArticleId articleId);

    List<Article> getAllUnknown(UserId userId);

    Article create(Article article);

    void update(Article article);

    void updateTitle(Article article);

    void delete(ArticleId articleId);
}
