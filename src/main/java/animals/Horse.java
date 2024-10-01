package animals;

import abc.Herbivorous;
import interfaces.ILand;
import Food.Grass;

public class Horse extends Herbivorous implements ILand {
  private static final String type = "Horse";


  public void eat(Grass grass) {
    super.eat(type, grass);
  }

  @Override
  public void walk() {
    System.out.println("Horse walk");
  }
}
