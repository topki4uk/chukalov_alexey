import commands.SortType;
import executor.SortExecutor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BubbleSortTest {
  @Test
  void testSort() {
    List<Integer> list = Arrays.asList(5, 7, 2, 6, 4, 2);
    SortExecutor executor = new SortExecutor();

    List<Integer> newList = executor.execute(SortType.BUBBLE_SORT, list);
    list.sort(null);
    assertEquals(newList, list);
  }

  @Test
  void testVeryBigListSort() {
    List<Integer> list = Arrays.asList(new Integer[100]);
    SortExecutor executor = new SortExecutor();
    assertThrows(IllegalArgumentException.class, () -> executor.execute(SortType.BUBBLE_SORT, list));
  }
}
