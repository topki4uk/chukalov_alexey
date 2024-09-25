package animals;

import Food.Beef;
import Food.FoodException;
import interfaces.ILand;
import interfaces.IMeat;
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
    } else {
      throw new FoodException("Tigers couldn't eat this!");
    }
  }
}
