package MyArray;

/***
 * Custom Array realisation with generics
 * @param <T>
 */
public class CustomArrayList<T> implements AbstractCustomArrayList<T> {
  private Object[] array;
  private int size;
  private int capacity;

  /***
   * Constructor of the class. Initialize all variables
   */
  public CustomArrayList(int initialCapacity) {
    initialize(initialCapacity);
  }

  /***
   * Constructor without params
   */
  public CustomArrayList() {
    initialize(1);
  }

  /***
   * Initialize all variables in class
   * @param initCapacity size of array in the beginning
   */
  private void initialize(int initCapacity) {
    size = 0;
    capacity = initCapacity;
    array = new Object[capacity];
  }

  public int size() {
    return size;
  }

  /***
   * Get the element of array by index.
   * @param i Index of element we need.
   * @return We cast to some type the element.
   */
  @Override
  public T get(int i) {
    return (T) array[i];
  }

  /***
   * When array`s size become too small
   * this func change array`s size.
   * (Makes it bigger twice)
   */
  private void resize() {
    capacity *= 2;

    Object[] arr = new Object[capacity];
    if (size >= 0)
      System.arraycopy(array, 0, arr, 0, size);
    array = arr;
  }

  /***
   * Remove element from array by index.
   * @param a Index of element.
   */
  @Override
  public void remove(int a) {
    if (size >= capacity) {
      resize();
    }
    for (int i = a; i < size; ++i) {
      array[i] = array[i + 1];
    }
    array[--size] = null;
  }

  /***
   * Add element to th end of array.
   * @param a Element we add.
   */
  @Override
  public void add(T a) {
    if (a == null) {
      throw new RuntimeException("Could not save null in array.");
    }

    if (size >= capacity) {
      resize();
    }

    array[size] = a;
    size++;
  }

  /***
   * Cool output for array
   * @return String of array`s elements.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < size; i++) {
      sb.append(array[i]).append(" ");
    }

    return sb.toString();
  }
}
