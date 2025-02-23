package com.example.demo.models.article.repositories;

import com.example.demo.models.article.Article;
import com.example.demo.models.article.ArticleId;
import com.example.demo.models.article.exceptions.ArticleNotFoundException;
import com.example.demo.models.user.UserId;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryArticleRepository implements ArticleRepository {
    List<Article> articles = new ArrayList<>(List.of(Article.ARTICLE_1, Article.ARTICLE_2));

    @Override
    public Optional<Article> findById(@NotNull ArticleId articleId) {
        for (Article article : articles) {
            if (article.id().equals(articleId)) {
                return Optional.of(article);
            }
        }
        throw new ArticleNotFoundException(articleId);
    }

    @Override
    public List<Article> getAllUnknown(UserId userId) {
        return articles;
    }

    @Override
    public @NotNull Article create(@NotNull Article article) {

      return article;
    }

    @Override
    public void update(@NotNull Article article) {

    }

    @Override
    public void updateTitle(@NotNull Article article) {
        update(article);
    }

    @Override
    public void delete(@NotNull ArticleId articleId) {

    }
}
