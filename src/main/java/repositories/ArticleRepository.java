package repositories;

import data.*;
import exceptions.ArticleNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ArticleRepository implements Repository {
  private final ArticleID articleID = new ArticleID(0);
  Map<ArticleID, Article> articles = new ConcurrentHashMap<>();

  @Override
  public ArticleID genereateID() {
    return articleID.generate();
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
  public void deleteArticle(ArticleID id) {
    Article article = articles.get(id);
    if (article == null) {
      throw new ArticleNotFoundException("Book with id=" + id + " not found!");
    }
    articles.remove(id);
  }

  @Override
  public synchronized ArticleID addArticle(Article article) {
    articles.put(articleID, article);
    return articleID;
  }

  @Override
  public void addCommentToArticle(ArticleID id, Comment comment) {
    Article article = articles.get(id);
    if (article == null) {
      throw new ArticleNotFoundException("Book with id=" + id + " not found!");
    }
    Article newArticle = getArticle(id).addComment(comment.setArticle(id));
    editArticle(id, newArticle);
  }

  @Override
  public void deleteCommentFromArticle(ArticleID articleId, Comment comment) {
    Article article = articles.get(articleId);
    if (article == null) {
      throw new ArticleNotFoundException("Book with id=" + articleId + " not found!");
    }
    editArticle(articleId, article.deleteComment(comment));
  }
}
