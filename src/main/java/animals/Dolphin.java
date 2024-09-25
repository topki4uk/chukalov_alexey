package animals;

import Food.FoodException;
import interfaces.IAquatic;
import interfaces.IMeat;
import interfaces.IPredator;
import Food.Fish;

public class Dolphin implements IAquatic, IPredator {

  @Override
  public void swim() {
    System.out.println("Dolphin swim");
  }

  @Override
  public void eat(IMeat fish) {
    if (fish instanceof Fish) {
      System.out.format("Dolphin eat %s\n", fish);
    } else {
      throw new FoodException("Dolphins couldn't eat this!");
    }
  }
}
