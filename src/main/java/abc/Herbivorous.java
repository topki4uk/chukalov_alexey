package abc;

import food.Grass;

public abstract class Herbivorous extends Animal {
  public void eat(String type, Grass grass) {
    System.out.printf("%s eat %s\n", type, grass.get());
  }
}
