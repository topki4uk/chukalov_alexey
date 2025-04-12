package com.example.homework.service;

import com.example.homework.exception.ArticleNotFoundException;
import com.example.homework.model.article.Article;
import com.example.homework.model.article.ArticleData;
import com.example.homework.model.article.ArticleId;
import com.example.homework.model.article.ArticleListData;
import com.example.homework.repository.ArticlesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticlesService {
    private final ArticlesRepository articleRepository;
    private final UserAuditsService userAuditsService;

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

    @Transactional(readOnly = true)
    public Article findById(Long articleId) {
        return articleRepository.findById(articleId)
            .orElseThrow(() -> new ArticleNotFoundException(new ArticleId(articleId)));
    }

    @Transactional()
    public Article create(ArticleData articleData) {
        Article article = new Article();

        article.setTitle(articleData.title());
        article.setUrl(articleData.url());
        article.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        article.setWebsiteId(articleData.websiteId());
        article.setTopicId(articleData.topicId());

        articleRepository.save(article);
        return article;
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
