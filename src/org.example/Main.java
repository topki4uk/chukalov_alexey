package org.example;

import my_array.CustomArrayList;

public class Main {
  public static void main(String[] args) {
    CustomArrayList<Integer> array = new CustomArrayList<>();

    array.add(10);
    array.add(4);
    array.add(7);

    System.out.println(array);
    System.out.println(array.get(1) + array.get(0));
    System.out.println(array.size());

    array.remove(1);
    array.add(22);

    System.out.println(array.size());
    System.out.println(array);
  }
}
