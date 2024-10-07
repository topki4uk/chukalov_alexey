package my_array;

/***
 * Interface of custom ArrayList
 * Contains 3 methods
 * @param <T> the type of elements in the array
 */
public interface AbstractCustomArrayList<T> {
  /**
   * Add an element to the end of the array
   * @param a the element with type T
   */
  void add(T a);

  /**
   * Get element of the array with index
   * @param index index of the element
   * @return element of the array by index
   * @throws IndexOutOfBoundsException if index out of range
   */
  T get(int index) throws IndexOutOfBoundsException;

  /**
   * Remove element of the array with index
   * @param index index of the element
   * @return element of the array by index
   * @throws IndexOutOfBoundsException if index out of range
   */
  T remove(int index) throws IndexOutOfBoundsException;
}
