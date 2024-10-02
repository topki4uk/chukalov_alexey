package main;

import Array.AbstractCustomArrayList;
import Array.CustomArrayList;

public class Main {
  public static void main(String[] args) {
    AbstractCustomArrayList<Double> array = new CustomArrayList<>(10);

    array.add(10.0);
    array.add(4.3);

    array.remove(0);

    System.out.println(array.get(0));
  }
}
