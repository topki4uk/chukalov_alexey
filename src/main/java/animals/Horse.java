package animals;

import abc.Herbivorous;
import interfaces.Walking;
import Food.Grass;

public class Horse extends Herbivorous implements Walking {
  private final String type = "Horse";

  public void eat(Grass grass) {
    super.eat(type, grass);
  }

  @Override
  public void walk() {
    System.out.printf("%s walk\n", type);
  }
}
