package com.example.demo.repository;

import com.example.demo.model.article.Article;
import com.example.demo.model.article.ArticleId;
import com.example.demo.exception.ArticleNotFoundException;
import com.example.demo.model.user.UserId;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryArticlesRepository implements ArticlesRepository {
    List<Article> articles = new ArrayList<>(List.of(Article.ARTICLE_1, Article.ARTICLE_2));
    private final RestTemplate restTemplate = new RestTemplate();
    private final WebClient webClient = WebClient.create();

    @Override
    public Optional<Article> findById(ArticleId articleId) {
        for (Article article : articles) {
            if (article.getId().equals(articleId)) {
                return Optional.of(article);
            }
        }

        restTemplate.getForObject("http://localhost:8080/articles/sync" + articleId, String.class);
        throw new ArticleNotFoundException(articleId);
    }

    @Override
    public List<Article> getAllUnknown(UserId userId) {
        return articles;
    }

    @Override
    public Article create(Article article) {
      return article;
    }

    @Override
    public void update(Article article) {
        webClient.get().uri("http://localhost:8080/articles/sync")
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }

    @Override
    public void updateTitle(Article article) {
        update(article);
    }

    @Override
    public void delete(ArticleId articleId) {

    }
}
