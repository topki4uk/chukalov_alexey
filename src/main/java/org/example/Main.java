package org.example;

import food.Beef;
import food.Fish;
import food.Grass;
import abc.Meet;
import animals.*;

public class Main {
  public static void main(String[] args) {
    Grass grass = new Grass();
    Meet beef = new Beef();
    Meet fish = new Fish();

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