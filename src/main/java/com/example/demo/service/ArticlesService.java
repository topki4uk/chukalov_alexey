package com.example.demo.service;

import com.example.demo.model.article.Article;
import com.example.demo.model.article.ArticleData;
import com.example.demo.model.article.ArticleId;
import com.example.demo.exception.ArticleNotFoundException;
import com.example.demo.model.article.ArticleListData;
import com.example.demo.repository.ArticlesRepository;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticlesService {
    private final ArticlesRepository articleRepository;
    private final CircuitBreaker breaker = CircuitBreaker.ofDefaults("apiCircuitBreaker");

    @Cacheable(value = "myCache")
    @Transactional(readOnly = true)
    public ArticleListData findAll() {
        List<ArticleData> articleDataList = new ArrayList<>();

        for (Article article : articleRepository.findAll()) {
            articleDataList.add(
                new ArticleData(
                    article.getTitle(),
                    article.getUrl(),
                    article.getTopicId(),
                    article.getWebsiteId()
                )
            );
        }

        return new ArticleListData(articleDataList);
    }

    @Cacheable(value = "myCache", key = "#articleId")
    @Transactional(readOnly = true)
    public Article findById(Long articleId) {
        return breaker.executeSupplier(() ->
            articleRepository
            .findById(articleId)
            .orElseThrow(() -> new ArticleNotFoundException(new ArticleId(articleId))));
    }

    @Transactional()
    public Article create(ArticleData articleData) {
        return breaker.executeSupplier(() -> {
            Article article = new Article();

            article.setTitle(articleData.title());
            article.setUrl(articleData.url());
            article.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            article.setWebsiteId(articleData.websiteId());
            article.setTopicId(articleData.topicId());

            articleRepository.save(article);
            return article;
        });
    }

    @Transactional()
    public void updateTitle(Long articleId, String title) {
        articleRepository.updateArticleTitle(articleId, title);
    }

    @Transactional()
    public void delete(Long articleId) {
        articleRepository.deleteArticlesById(articleId);
    }
}
