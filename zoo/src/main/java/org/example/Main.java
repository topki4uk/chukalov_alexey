package org.example;

import Food.Beef;
import Food.Fish;
import Food.Grass;
import interfaces.IMeat;
import animals.*;

public class Main {
  public static void main(String[] args) {
    Grass grass = new Grass();
    IMeat beef = new Beef();
    IMeat fish = new Fish();

    Horse horse = new Horse();
    Camel camel = new Camel();
    Tiger tiger = new Tiger();
    Dolphin dolphin = new Dolphin();
    Eagle eagle = new Eagle();

    horse.eat(grass);
    horse.walk();

    camel.eat(grass);
    camel.walk();

    tiger.eat(beef);
    tiger.walk();

    dolphin.swim();
    dolphin.eat(fish);

    eagle.fly();
    eagle.eat(beef);
    eagle.eat(fish);
  }
}