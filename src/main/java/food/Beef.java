package food;

import abc.Meet;

public class Beef extends Meet {
  @Override
  public String get() {
    return Food.BEEF.getTitle();
  }
}
