package repositories;

import data.*;
import exceptions.ArticleNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ArticleRepository implements Repository {
  private final AtomicLong articleID = new AtomicLong(0);
  Map<ArticleID, Article> articles = new ConcurrentHashMap<>();

  @Override
  public ArticleID generateID() {
    return new ArticleID(articleID.incrementAndGet());
  }

  @Override
  public List<Article> getAll() {
    return new ArrayList<>(articles.values());
  }

  @Override
  public Article getArticle(ArticleID id) {
    Article article = articles.get(id);
    if (article == null) {
      throw new ArticleNotFoundException("Book with id=" + id + " not found!");
    }
    return article;
  }

  @Override
  public synchronized void editArticle(ArticleID id, Article newArticle) {
    Article currentArticle = articles.get(id);
    if (currentArticle == null) {
      throw new ArticleNotFoundException("Book with id=" + id + " not found!");
    }
    articles.put(id, newArticle);
  }

  @Override
  public synchronized void deleteArticle(ArticleID id) {
    Article article = articles.get(id);
    if (article == null) {
      throw new ArticleNotFoundException("Book with id=" + id + " not found!");
    }
    articles.remove(id);
  }

  @Override
  public synchronized ArticleID addArticle(Article article) {
    articles.put(article.getId(), article);
    return article.getId();
  }

  @Override
  public void addCommentToArticle(ArticleID id, Comment comment) {
    Article article = articles.get(id);
    if (article == null) {
      throw new ArticleNotFoundException("Article with id=" + id + " not found!");
    }
    Article newArticle = article.addComment(comment.setArticle(id));
    editArticle(id, newArticle);
  }

  @Override
  public void deleteCommentFromArticle(ArticleID articleId, Comment comment) {
    Article article = articles.get(articleId);
    if (article == null) {
      throw new ArticleNotFoundException("Article with id=" + articleId + " not found!");
    }
    editArticle(articleId, article.deleteComment(comment));
  }
}
