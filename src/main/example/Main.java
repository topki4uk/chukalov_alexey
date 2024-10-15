package example;

import commands.SortType;
import executor.SortExecutor;

import java.util.Arrays;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    SortExecutor executor = new SortExecutor();
    List<Integer> list = Arrays.asList(6, 5, 9, 1, 4, 5);
    List<Integer> sortList = executor.execute(SortType.BUBBLE_SORT, list);
    System.out.println(sortList);
  }
}
