package animals;

import food.FoodException;
import abc.Meet;
import abc.Predator;
import interfaces.Swimming;
import food.Fish;

public class Dolphin extends Predator implements Swimming {
  private final String type = "Dolphin";

  @Override
  public void swim() {
    System.out.printf("%s swim\n", type);
  }

  public void eat(Meet fish) {
    if (fish instanceof Fish) {
      super.eat(type, fish);
      return;
    }
    throw new FoodException("Couldn't eat this!");
  }
}
