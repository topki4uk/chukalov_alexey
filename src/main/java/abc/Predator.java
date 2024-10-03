package abc;

public abstract class Predator extends Animal {
  public void eat(String type, Meet meet) {
    System.out.printf("%s eat %s\n", type, meet);
  }

}
