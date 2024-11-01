package data;

public class ArticleID extends ID {
  public ArticleID(long id) {
    super(id);
  }

  public ArticleID generate() {
    return new ArticleID(id.incrementAndGet());
  }

  public long getID() {
    return super.getID();
  }
}
