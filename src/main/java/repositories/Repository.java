package repositories;

import data.*;

import java.util.List;

public interface Repository {
  ArticleID generateID();

  List<Article> getAll();
  Article getArticle(ArticleID id);
  void editArticle(ArticleID id, Article newArticle);
  void deleteArticle(ArticleID id);
  ArticleID addArticle(Article article);
  void addCommentToArticle(ArticleID id, Comment comment);
  void deleteCommentFromArticle(ArticleID articleId, Comment comment);

}
