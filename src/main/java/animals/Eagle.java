package animals;

import abc.Meet;
import abc.Predator;
import interfaces.Flying;

public class Eagle extends Predator implements Flying {
  protected final String type = "Eagle";

  @Override
  public void fly() {
    System.out.printf("%s walk\n", type);
  }

  public void eat(Meet meat) {
    super.eat(type, meat);
  }
}
