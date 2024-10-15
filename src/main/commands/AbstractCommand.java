package commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractCommand {
  protected List<Integer> getCopy(List<Integer> list) {
    List<Integer> newList = Arrays.asList(new Integer[list.size()]);
    Collections.copy(newList, list);
    return newList;
  }

  public abstract List<Integer> sort(List<Integer> list) throws IllegalArgumentException;
}
