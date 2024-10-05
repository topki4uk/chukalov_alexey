package MyArray;

/***
 * Interface of custom ArrayList
 * Contains 3 methods
 * @param <T>
 */
public interface AbstractCustomArrayList<T> {
  void add(T a);
  T get(int i);
  void remove(int i);
}
