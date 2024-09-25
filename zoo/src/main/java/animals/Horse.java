package animals;

import interfaces.IHerbivorous;
import interfaces.ILand;
import Food.Grass;

public class Horse implements IHerbivorous, ILand {

  @Override
  public void eat(Grass grass) {
    System.out.format("Horse eat %s\n", grass);
  }

  @Override
  public void walk() {
    System.out.println("Horse walk");
  }
}
