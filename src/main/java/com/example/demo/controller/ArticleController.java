package com.example.demo.controller;


import com.example.demo.models.article.ArticleId;
import com.example.demo.models.article.ArticleListData;
import com.example.demo.models.article.Article;
import com.example.demo.models.article.ArticleData;
import com.example.demo.models.article.ArticleTitleData;
import com.example.demo.models.user.UserId;
import com.example.demo.operations.ArticleOperations;
import com.example.demo.services.ArticleService;
import com.example.demo.services.TopicService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/articles")
@Tag(name = "Article API", description = "Управление статьями")
public class ArticleController implements ArticleOperations {
    private static final Logger LOG = LoggerFactory.getLogger(ArticleController.class);

    private final ArticleService articleService;
    private final TopicService topicService;

    public ArticleController(ArticleService articleService, TopicService topicService) {
        this.articleService = articleService;
        this.topicService = topicService;
    }

    @Override
    public ResponseEntity<ArticleListData> getUserArticles(Long userId) {
        List<Article> articles = articleService.getAllUnknown(new UserId(userId));
        List<ArticleData> articleDataList = new ArrayList<>();

        for (Article article : articles) {
            String topicDescription = topicService.findById(article.topicId()).description();
            articleDataList.add(new ArticleData(article.title(), article.url(), article.createdAt(), topicDescription));
        }
        LOG.debug("Articles of user with id={} was found successfully", userId);
    return ResponseEntity.ok(new ArticleListData(articleDataList));
    }

    @Override
    public ResponseEntity<String> updateArticleTitle(Long articleId, ArticleTitleData titleData) {
        Optional<Article> article = articleService.findById(new ArticleId(articleId));
        Article newArticle = article.get().withTitle(titleData.title());
        articleService.updateTitle(newArticle);
        LOG.debug("Article`s title with id={} was updated", articleId);
        return ResponseEntity.ok("Title updated");
    }
}
