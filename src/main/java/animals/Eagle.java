package animals;

import abc.Meet;
import abc.Predator;
import interfaces.IFlying;

public class Eagle extends Predator implements IFlying {
  protected final String type = "Eagle";

  @Override
  public void fly() {
    System.out.printf("%s walk\n", type);
  }

  public void eat(Meet meat) {
    super.eat(type, meat);
  }
}
