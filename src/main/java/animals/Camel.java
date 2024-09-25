package animals;

import interfaces.IHerbivorous;
import interfaces.ILand;
import Food.Grass;

public class Camel implements IHerbivorous, ILand {
  @Override
  public void eat(Grass grass) {
    System.out.format("Camel eat %s\n", grass);
  }

  @Override
  public void walk() {
    System.out.println("Camel walk");
  }
}
