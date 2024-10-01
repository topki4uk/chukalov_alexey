package animals;

import abc.Herbivorous;
import interfaces.ILand;
import Food.Grass;

public class Camel extends Herbivorous implements ILand {
  private final String type = "Camel";

  public void eat(Grass grass) {
    super.eat(type, grass);
  }

  @Override
  public void walk() {
    System.out.printf("%s walk\n", type);
  }
}
