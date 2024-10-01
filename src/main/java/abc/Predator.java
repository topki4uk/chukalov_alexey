package abc;

public abstract class Predator {
  public void eat(String type, Meet meet) {
    System.out.printf("%s eat %s\n", type, meet);
  }

}
