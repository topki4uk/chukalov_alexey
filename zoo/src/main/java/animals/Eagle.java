package animals;

import interfaces.IMeat;
import interfaces.IFlying;
import interfaces.IPredator;

public class Eagle implements IFlying, IPredator {
  @Override
  public void fly() {
    System.out.println("Eagle fly");
  }

  @Override
  public void eat(IMeat meat) {
    System.out.format("Eagle eat %s\n", meat);
  }
}
