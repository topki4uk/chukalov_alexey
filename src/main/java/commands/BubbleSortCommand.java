package commands;

import java.util.List;

public class BubbleSortCommand extends AbstractCommand {
  public static SortType SORT_TYPE = SortType.BUBBLE_SORT;
  public static int ELEMENT_LIMIT = 20;

  @Override
  public List<Integer> sort(List<Integer> list) {
    if (list.size() > ELEMENT_LIMIT) {
      throw new IllegalArgumentException();
    }

    List<Integer> arr = getCopy(list);

    for (int i = 0; i < arr.size(); i++) {
      for (int j = i + 1; j < arr.size(); j++) {
        if (arr.get(i) > arr.get(j)) {
          int temp = arr.get(i);
          arr.set(i, arr.get(j));
          arr.set(j, temp);
        }
      }
    }

    return arr;
  }
}
