package animals;

import Food.Beef;
import interfaces.IMeat;
import interfaces.ILand;
import interfaces.IPredator;

public class Tiger implements ILand, IPredator {
  @Override
  public void walk() {
    System.out.println("Tiger walk");
  }

  @Override
  public void eat(IMeat beef) {
    if (beef instanceof Beef) {
      System.out.format("Tiger eat %s\n", beef);
    }
  }
}
