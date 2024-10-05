package food;

import abc.Meet;

public class Fish extends Meet {
  @Override
  public String get() {
    return Food.FISH.getTitle();
  }
}
