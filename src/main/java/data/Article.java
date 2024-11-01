package data;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Article {
  private final ArticleID id;
  private final String title;
  private final Set<String> tags;
  private final List<Comment> comments;

  public Article(ArticleID id, String title, Set<String> tags, List<Comment> comments) {
    this.id = id;
    this.title = title;
    this.tags = tags;
    this.comments = comments;
  }

  public Article(ArticleID id, ArticleBody body) {
    this.id = id;
    this.title = body.title();
    this.tags = body.tags();
    this.comments = body.comments();
  }

  public Article addComment(Comment comment) {
    Article newArticle = new Article(id, title, tags, comments);
    newArticle.comments.add(comment);
    return newArticle;
  }

  public Article deleteComment(Comment comment) {
    Article newArticle = new Article(id, title, tags, comments);
    newArticle.comments.remove(comment);
    return newArticle;
  }

  @Override
  public String toString() {
    return String.format("ID=%s title=%s tags=%s comments=%s", id, title, tags, comments);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Article article = (Article) o;
    return Objects.equals(id, article.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, tags, comments);
  }
}
