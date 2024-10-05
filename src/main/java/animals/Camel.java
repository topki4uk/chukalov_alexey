package animals;

import abc.Herbivorous;
import interfaces.Walking;
import food.Grass;

public class Camel extends Herbivorous implements Walking {
  private static final String type = "Camel";

  public void eat(Grass grass) {
    super.eat(type, grass);
  }

  @Override
  public void walk() {
    System.out.printf("%s walk\n", type);
  }
}
