package animals;

import Food.FoodException;
import abc.Meet;
import abc.Predator;
import interfaces.IAquatic;
import Food.Fish;

public class Dolphin extends Predator implements IAquatic {
  private final String type = "Dolphin";

  @Override
  public void swim() {
    System.out.printf("%s swim\n", type);
  }

  public void eat(Meet fish) {
    if (!(fish instanceof Fish)) {
      throw new FoodException("Couldn't eat this!");
    }

    super.eat(type, fish);
  }
}
