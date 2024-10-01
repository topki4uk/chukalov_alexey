package animals;

import Food.Beef;
import Food.FoodException;
import abc.Meet;
import abc.Predator;
import interfaces.ILand;

public class Tiger extends Predator implements ILand {
  private final String type = "Tiger";

  @Override
  public void walk() {
    System.out.printf("%s walk\n", type);
  }

  public void eat(Meet beef) {
    if (!(beef instanceof Beef)) {
      throw new FoodException("Couldn't eat this!");
    }

    super.eat(type, beef);
  }
}
