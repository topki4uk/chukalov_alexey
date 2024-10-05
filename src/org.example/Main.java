package org.example;

import MyArray.CustomArrayList;

public class Main {
  public static void main(String[] args) {
    CustomArrayList<Double> array = new CustomArrayList<>();

    array.add(10.0);
    array.add(4.3);

    array.remove(0);

    System.out.println(array.get(0));
  }
}
