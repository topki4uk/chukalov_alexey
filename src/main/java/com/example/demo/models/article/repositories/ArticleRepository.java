package com.example.demo.models.article.repositories;

import com.example.demo.models.article.Article;
import com.example.demo.models.article.ArticleId;
import com.example.demo.models.user.UserId;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    Optional<Article> findById(@NotNull ArticleId articleId);

    List<Article> getAllUnknown(UserId userId);

    @NotNull Article create(@NotNull Article article);

    void update(@NotNull Article article);

    void delete(@NotNull ArticleId articleId);
}
