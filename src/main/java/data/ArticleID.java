package data;

import java.util.Objects;

public class ArticleID {
  private long id;

  public ArticleID(long id) {
    this.id = id;
  }

  public ArticleID generate() {
    return new ArticleID(++id);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    ArticleID articleID = (ArticleID) o;
    return Objects.equals(id, articleID.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  public long getID() {
    return id;
  }
}
