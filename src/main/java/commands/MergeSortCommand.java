package commands;

import java.util.Collections;
import java.util.List;

public class MergeSortCommand extends AbstractCommand {
  public static SortType SORT_TYPE = SortType.MERGE_SORT;
  public static int ELEMENT_LIMIT = 500;

  @Override
  public List<Integer> sort(List<Integer> list) {
    if (list.size() > ELEMENT_LIMIT) {
      throw new IllegalArgumentException();
    }

    List<Integer> arr = getCopy(list);
    Collections.sort(arr);
    return arr;
  }
}
