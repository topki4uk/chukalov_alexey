package com.example.demo.controller;


import com.example.demo.model.article.ArticleListData;
import com.example.demo.model.article.Article;
import com.example.demo.model.article.ArticleData;
import com.example.demo.model.article.ArticleTitleData;
import com.example.demo.operation.ArticleOperations;
import com.example.demo.service.ArticlesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/articles")
@Tag(name = "Article API", description = "Управление статьями")
public class ArticlesController implements ArticleOperations {
    private static final Logger LOG = LoggerFactory.getLogger(ArticlesController.class);

    private final ArticlesService articleService;

    public ArticlesController(ArticlesService articleService) {
        this.articleService = articleService;
    }

    @Override
    public ResponseEntity<ArticleListData> getAllArticles() {
        ArticleListData articles = articleService.findAll();

        LOG.debug("Articles count: {}", articles.articleDataList().size());
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ArticleData> getArticleById(Long articleId) {
        Article article = articleService.findById(articleId);
        ArticleData articleData = new ArticleData(
            article.getTitle(),
            article.getUrl(),
            article.getTopicId(),
            article.getWebsiteId()
        );

        LOG.debug("Article data: {}", articleData);
        return new ResponseEntity<>(articleData, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateArticleTitle(Long articleId, ArticleTitleData newTitle) {
        articleService.updateTitle(articleId, newTitle.title());

        LOG.debug("Article`s title with id={} was updated", articleId);
        return ResponseEntity.ok("Title updated");
    }

    @Override
    public ResponseEntity<Article> createArticle(ArticleData articleData) {
        Article article = articleService.create(articleData);

        LOG.debug("Article`s title with id={} was created", article.getId());
        return new ResponseEntity<>(article, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> deleteArticle(Long articleId) {
        articleService.delete(articleId);

        LOG.debug("Article`s id with id={} was deleted", articleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
