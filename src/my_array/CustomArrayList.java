package my_array;

import org.jetbrains.annotations.NotNull;

/***
 * Custom Array realisation with generics
 * @param <T> {@inheritDoc}
 */
public class CustomArrayList<T> implements AbstractCustomArrayList<T> {
  private static final int INITIAL_CAPACITY = 10;
  private Object[] array;
  private int size;
  private int capacity;

  /***
   Constructor of the class. Initialize all variables
   * @param initialCapacity initial capacity of the array
   */
  public CustomArrayList(int initialCapacity) {
    if (initialCapacity > 0) {
      throw new IllegalArgumentException("This is incorrect value of capacity!");
    }
    initialize(initialCapacity);
  }

  /**
   Constructor without params
   */
  public CustomArrayList() {
    initialize(INITIAL_CAPACITY);
  }

  /**
   * Initialize all variables in class
   * @param initCapacity size of array at the beginning
   */
  private void initialize(int initCapacity) {
    size = 0;
    capacity = initCapacity;
    array = new Object[capacity];
  }

  /**
   * Checks that index in range
   * @param i index that we proof
   * @return true when i in range, else - false
   */
  private boolean checkIndex(int i) {
    if (i < 0)
      return false;

    return i < size;
  }

  /**
   * Size of array
   * @return current size
   */
  public int size() {
    return size;
  }

  /**
   * Get the element of array by index.
   * @param index {@inheritDoc}
   * @return {@inheritDoc}
   */
  @Override
  public T get(int index) {
    if (!checkIndex(index))
      throw new IndexOutOfBoundsException();

    return (T) array[index];
  }

  /**
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

  /**
   * Remove element from array by index.
   *
   * @param index {@inheritDoc}
   * @return {@inheritDoc}
   */
  @Override
  public T remove(int index) {
    if (!checkIndex(index))
      throw new IndexOutOfBoundsException();

    T el = (T) array[index];
    for (int i = index; i < size; ++i) {
      array[i] = array[i + 1];
    }

    array[--size] = null;
    return el;
  }

  /**
   * {@inheritDoc}
   *
   * @param a {@inheritDoc}
   */
  @Override
  public void add(@NotNull T a) {
    if (size >= capacity) {
      resize();
    }

    array[size] = a;
    size++;
  }

  /**
   * Cool output for array
   *
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
