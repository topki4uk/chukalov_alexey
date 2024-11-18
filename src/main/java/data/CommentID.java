package data;

import java.util.Objects;

public class CommentID {
  private long id;

  public CommentID(long id) {
    this.id = id;
  }

  public CommentID generate() {
    return new CommentID(++id);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    CommentID commentID = (CommentID) o;
    return Objects.equals(id, commentID.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  public long getID() {
    return id;
  }

  @Override
  public String toString() {
    return "id: " + id;
  }
}
