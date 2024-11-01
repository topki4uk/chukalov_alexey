package services;

import data.*;
import repositories.ArticleRepository;
import repositories.CommentRepository;

import java.util.List;
import java.util.Set;

public class ArticleService {
  private final ArticleRepository articleRepository;
  private final CommentRepository commentRepository;

  public ArticleService(ArticleRepository articleRepository, CommentRepository commentRepository) {
    this.articleRepository = articleRepository;
    this.commentRepository = commentRepository;
  }

  public List<Article> getAll() {
    return articleRepository.getAll();
  }

  public Article getArticleById(ArticleID id) {
    return articleRepository.getArticle(id);
  }

  public ArticleID addArticle(ArticleBody body) {
    ArticleID id = articleRepository.genereateID();
    Article article = new Article(id, body);
    return articleRepository.addArticle(article);
  }

  public ArticleID addArticle(String title, Set<String> tags, List<Comment> comments) {
    ArticleBody body = new ArticleBody(title, tags, comments);
    return addArticle(body);
  }

  public void editArticle(ArticleID id, ArticleBody body) {
    Article article = new Article(id, body);
    articleRepository.editArticle(id, article);
  }

  public void deleteArticle(ArticleID id) {
    articleRepository.deleteArticle(id);
  }

  public CommentID addCommentToArticle(ArticleID id, String text) {
    Article article = getArticleById(id);
    CommentID commentId = commentRepository.genereateID();

    Comment comment = new Comment(commentId, id, text);
    articleRepository.addCommentToArticle(id, comment);
    return commentId;
  }

  public void deleteComment(ArticleID articleId, CommentID commentId) {
    Comment comment = commentRepository.getComment(commentId);
    commentRepository.deleteComment(commentId);
    articleRepository.deleteCommentFromArticle(articleId, comment);
  }
}
