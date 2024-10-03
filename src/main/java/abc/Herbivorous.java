package abc;

import Food.Grass;

public abstract class Herbivorous extends Animal {
  public void eat(String type, Grass grass) {
    System.out.printf("%s eat %s\n", type, grass);
  }
}
