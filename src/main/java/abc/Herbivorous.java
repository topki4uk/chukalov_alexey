package abc;

import Food.Grass;

public abstract class Herbivorous {
  public void eat(String type, Grass grass) {
    System.out.printf("%s eat %s\n", type, grass);
  }
}
