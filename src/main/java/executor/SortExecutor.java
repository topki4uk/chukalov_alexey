package executor;

import commands.AbstractCommand;
import commands.BubbleSortCommand;
import commands.MergeSortCommand;
import commands.SortType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortExecutor {
  private final Map<SortType, AbstractCommand> commands = new HashMap<>()
  {
    {
      put(SortType.BUBBLE_SORT, new BubbleSortCommand());
      put(SortType.MERGE_SORT, new MergeSortCommand());
    }
  };

  public SortExecutor() {
  }

  public List<Integer> execute(SortType type, List<Integer> list) {
    return commands.get(type).sort(list);
  }
}
