package data;

import java.util.Objects;

public class Comment {
  private final CommentID id;
  private final ArticleID articleId;
  private final String text;

  public Comment(CommentID id, ArticleID articleId, String text) {
    this.id = id;
    this.articleId = articleId;
    this.text = text;
  }

  public Comment setArticle(ArticleID articleId) {
    return new Comment(id, articleId, text);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Comment comment = (Comment) o;
    return Objects.equals(id, comment.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, articleId, text);
  }
}
