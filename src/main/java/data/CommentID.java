package data;

public class CommentID extends ID {
  public CommentID(long id) {
    super(id);
  }

  public CommentID generate() {
    return new CommentID(id.incrementAndGet());
  }

  public long getID() {
    return super.getID();
  }
}
