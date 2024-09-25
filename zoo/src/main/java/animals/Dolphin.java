package animals;

import interfaces.IMeat;
import interfaces.IAquatic;
import Food.Fish;
import interfaces.IPredator;

public class Dolphin implements IAquatic, IPredator {

  @Override
  public void swim() {
    System.out.println("Dolphin swim");
  }

  @Override
  public void eat(IMeat fish) {
    if (fish instanceof Fish) {
      System.out.format("Dolphin eat %s\n", (Fish) fish);
    }
  }
}
