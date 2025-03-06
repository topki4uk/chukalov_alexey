package com.example.demo.controller;


import com.example.demo.model.article.ArticleId;
import com.example.demo.model.article.ArticleListData;
import com.example.demo.model.article.Article;
import com.example.demo.model.article.ArticleData;
import com.example.demo.model.article.ArticleTitleData;
import com.example.demo.model.user.UserId;
import com.example.demo.operation.ArticleOperations;
import com.example.demo.service.ArticlesService;
import com.example.demo.service.TopicsService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
public class ArticlesController implements ArticleOperations {
    private static final Logger LOG = LoggerFactory.getLogger(ArticlesController.class);

    private final ArticlesService articleService;
    private final TopicsService topicService;

    public ArticlesController(ArticlesService articleService, TopicsService topicService) {
        this.articleService = articleService;
        this.topicService = topicService;
    }

    @Override
    public ResponseEntity<ArticleListData> getUserArticles(Long userId) {
        List<Article> articles = articleService.getAllUnknown(new UserId(userId));
        List<ArticleData> articleDataList = new ArrayList<>();

        for (Article article : articles) {
            String topicDescription = topicService
                .findById(article.getTopicId())
                .join()
                .getDescription();

            articleDataList.add(new ArticleData(article.getTitle(), article.getUrl(), article.getCreatedAt(), topicDescription));
        }

        LOG.debug("Articles of user with id={} was found successfully", userId);
        return ResponseEntity.ok(new ArticleListData(articleDataList));
    }

    @Override
    public ResponseEntity<String> updateArticleTitle(Long articleId, ArticleTitleData titleData) {
        Article article = articleService.findById(new ArticleId(articleId));

        Article newArticle = article.toBuilder()
            .title(titleData.title())
            .build();
        articleService.updateTitle(newArticle);

        LOG.debug("Article`s title with id={} was updated", articleId);
        return ResponseEntity.ok("Title updated");
    }
}
